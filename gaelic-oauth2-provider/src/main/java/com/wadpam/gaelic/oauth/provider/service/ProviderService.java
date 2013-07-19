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
import com.wadpam.gaelic.security.SecurityDetails;
import com.wadpam.gaelic.security.SecurityDetailsService;
import java.util.Collections;
import java.util.Date;
import java.util.UUID;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    
    public static final int ERR_BASE = GaelicServlet.ERROR_CODE_OAUTH2_PROVIDER_BASE;
    public static final int ERR_MISSING_REDIRECT_URI = ERR_BASE;
    public static final int ERR_USERNAME_CONFLICT = ERR_BASE+1;
    public static final int ERR_USERNAME_REQUIRED = ERR_BASE+2;
    
    static final Logger LOG = LoggerFactory.getLogger(ProviderService.class);
    
    private Do2pClientDao clientDao;
    private Do2pProfileDao profileDao;
    private Do2pTokenDao tokenDao;
    
    /** Implicit access tokens should live for 90 minutes */
    private long implicitTTL = 90L*3600L*1000L;

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
            return null;
        }
        
        final Do2pProfile profile = profileDao.findByUsername(username);
        if (null != profile) {
            final String secret = encryptPassword(password, profile.getId());
            if (secret.equals(profile.getSecret())) {
                return profile;
            }
        }
        return null;
    }
    
    public static String encryptPassword(String password, Long id) {
        final String base = String.format("%dSaltyPeas%sAndNonsense%d",
                id, password, id);
        final String hash = DigestUtils.sha1Hex(base);
        return hash;
    }

    public Do2pClient getClient(String clientId) {
        Long id = Long.parseLong(clientId);
        Do2pClient client = clientDao.findByPrimaryKey(id);
        return client;
    }

    public String getImplicitToken(Do2pClient client, String redirectUri, Do2pProfile do2pProfile) {
        UUID uuid = UUID.randomUUID();
        final String accessToken = uuid.toString();
        
        Date expiryDate = new Date(System.currentTimeMillis() + implicitTTL);
        Object profileKey = profileDao.getPrimaryKey(do2pProfile);
//        LOG.debug("  profileKey is {} for profile {}", profileKey, do2pProfile);
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

    public long getImplicitTTL() {
        return implicitTTL;
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
