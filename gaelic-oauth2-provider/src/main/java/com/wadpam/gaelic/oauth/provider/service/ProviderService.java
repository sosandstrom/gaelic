/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.oauth.provider.service;

import com.wadpam.gaelic.GaelicServlet;
import com.wadpam.gaelic.oauth.provider.dao.Do2pClientDao;
import com.wadpam.gaelic.oauth.provider.dao.Do2pProfileDao;
import com.wadpam.gaelic.oauth.provider.dao.Do2pTokenDao;
import com.wadpam.gaelic.oauth.provider.domain.Do2pClient;
import com.wadpam.gaelic.oauth.provider.domain.Do2pProfile;
import com.wadpam.gaelic.oauth.provider.domain.Do2pToken;
import com.wadpam.gaelic.oauth.provider.json.JAccessTokenResponse;
import com.wadpam.gaelic.security.SecurityDetails;
import com.wadpam.gaelic.security.SecurityDetailsService;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jsr107cache.Cache;
import net.sf.jsr107cache.CacheException;
import net.sf.jsr107cache.CacheFactory;
import net.sf.jsr107cache.CacheManager;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sosandstrom
 */
public class ProviderService implements SecurityDetailsService {
    public static final String COOKIE_SIGNIN = "_gaelic_signin";
    public static final String PARAM_USERNAME = "username";
    public static final String PARAM_PASSWORD = "password";
    
    public static final String TOKEN_TYPE = "OAuth";
    
    public static final int ERR_BASE = GaelicServlet.ERROR_CODE_OAUTH2_PROVIDER_BASE;
    public static final int ERR_MISSING_REDIRECT_URI = ERR_BASE;
    public static final int ERR_USERNAME_CONFLICT = ERR_BASE+1;
    public static final int ERR_USERNAME_REQUIRED = ERR_BASE+2;
    
    static final Logger LOG = LoggerFactory.getLogger(ProviderService.class);
    
    private Do2pClientDao clientDao;
    private Do2pProfileDao profileDao;
    private Do2pTokenDao tokenDao;
    
    /** Implicit access tokens should live for 90 minutes */
    private long implicitTTL = 90L*60L*1000L;
    /** Implicit access tokens should live for 14 days */
    private long confidentialTTL = 14L*24L*60L*60L*1000L;
    
    private Cache cache;

    public ProviderService() {
        try {
            CacheFactory factory = CacheManager.getInstance().getCacheFactory();
            cache = factory.createCache(Collections.EMPTY_MAP);
        } catch (CacheException ex) {
            LOG.error("Could not initialize cache", ex);
        }
    }
    
    public Do2pProfile authenticate(HttpServletRequest request) {
        // is this the form POST ?
        String username = request.getParameter(PARAM_USERNAME);
        String password = request.getParameter(PARAM_PASSWORD);
        
        // check cookie
        if (null == username && null != request.getCookies()) {
            for (Cookie cookie : request.getCookies()) {
                if (COOKIE_SIGNIN.equals(cookie.getName())) {
                    
                }
            }
        }
        
        if (null == username || null == password) {
            LOG.debug("No credentials in request.");
            return null;
        }
        
        final Do2pProfile profile = profileDao.findByUsername(username);
        if (null != profile) {
            final String secret = encryptPassword(password, profile.getId());
            if (secret.equals(profile.getSecret())) {
                return profile;
            }
            LOG.debug("Password mismatch.");
        }
        else {
            LOG.debug("No such user for {}", username);
        }
        return null;
    }
    
    public static String encryptPassword(String plain, long salt) {
        final String base = String.format("%dSaltyPeas%sAndNonsense%d",
                salt, plain, salt);
        final String hash = DigestUtils.sha1Hex(base);
        return hash;
    }

    public void exchangeCodeForToken(String code, String redirectUri, 
            Do2pClient client, JAccessTokenResponse body) {
        final AuthorizationHolder holder = (AuthorizationHolder) cache.remove(code);
        if (null != holder && client.getId().equals(holder.getClientId()) && 
                redirectUri.equals(holder.getRedirect_uri())) {
            
            UUID uuid = UUID.randomUUID();
            body.setAccess_token(uuid.toString());
            uuid = UUID.randomUUID();
            body.setRefresh_token(uuid.toString());
            body.setExpires_in((int) confidentialTTL / 1000);
            body.setToken_type(TOKEN_TYPE);
            
            Object profileKey = profileDao.getPrimaryKey(null, holder.getProfileId());
            Date expiryDate = new Date(System.currentTimeMillis() + confidentialTTL);
            Do2pToken token = tokenDao.persist(profileKey, null,
                    body.getAccess_token(), client.getId(), expiryDate, 
                    body.getRefresh_token(), null);
        }
    }
    
    public String getAuthorizationCode(Do2pClient client, String redirectUri, Do2pProfile do2pProfile) {
        final UUID uuid = UUID.randomUUID();
        final String code = uuid.toString().substring(0, 8);
        
        final AuthorizationHolder holder = new AuthorizationHolder(client.getId(), 
                code, redirectUri, profileDao.getSimpleKey(do2pProfile));
        cache.put(code, holder);
        
        return code;
    }

    public Do2pClient getClient(String clientId) {
        Long id = Long.parseLong(clientId);
        Do2pClient client = clientDao.findByPrimaryKey(id);
        return client;
    }

    public String getImplicitToken(Do2pClient client, String redirectUri, Do2pProfile do2pProfile) {
        final UUID uuid = UUID.randomUUID();
        final String accessToken = uuid.toString();
        
        Date expiryDate = new Date(System.currentTimeMillis() + implicitTTL);
        Object profileKey = profileDao.getPrimaryKey(do2pProfile);
        Do2pToken token = tokenDao.persist(profileKey, null,
                accessToken, client.getId(), expiryDate, null, null);
        
        return accessToken;
    }

    /** To support the interceptor protecting profiles */
    @Override
    public SecurityDetails loadUserDetailsByUsername(HttpServletRequest request, HttpServletResponse response, String uri, String authValue, Object userKey) {
        Do2pToken token = tokenDao.findByAccessToken(authValue);
        if (null == token) {
            LOG.debug("No token found for {}", authValue);
            return null;
        }
        
        Object profileKey = tokenDao.getParentKey(token);
//        LOG.debug("   profileKey is {} for token {}", profileKey, token);
        final Long profileId = profileDao.getSimpleKeyByPrimaryKey(profileKey);
        return BUILDER.with(profileId.toString())
                .userKey(profileKey)
                .password(authValue)
                .roles(Collections.EMPTY_LIST)
                .build();
    }

    public void refreshToken(String refreshToken, Do2pClient client, JAccessTokenResponse body) {
        final Do2pToken token = tokenDao.findByRefreshToken(refreshToken);
        
        if (null != token && client.getId().equals(token.getClientId())) {
            UUID uuid = UUID.randomUUID();
            body.setAccess_token(uuid.toString());
            body.setRefresh_token(refreshToken);
            body.setExpires_in((int) confidentialTTL / 1000);
            body.setToken_type(TOKEN_TYPE);
            
            token.setAccessToken(body.getAccess_token());
            token.setExpiryDate(new Date(System.currentTimeMillis() + confidentialTTL));
            tokenDao.update(token);
        }
    }

    public static class AuthorizationHolder implements Serializable {
        private final Long clientId;
        private final String code;
        private final String redirect_uri;
        private final Long profileId;

        public AuthorizationHolder(Long clientId, String code, String redirect_uri,
                Long profileId) {
            this.clientId = clientId;
            this.code = code;
            this.redirect_uri = redirect_uri;
            this.profileId = profileId;
        }

        public Long getClientId() {
            return clientId;
        }

        public String getCode() {
            return code;
        }

        public String getRedirect_uri() {
            return redirect_uri;
        }

        public Long getProfileId() {
            return profileId;
        }
        
    }

    public long getImplicitTTL() {
        return implicitTTL;
    }

    public long getConfidentialTTL() {
        return confidentialTTL;
    }

    public void setProfileDao(Do2pProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    public void setClientDao(Do2pClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public void setTokenDao(Do2pTokenDao tokenDao) {
        this.tokenDao = tokenDao;
    }

}
