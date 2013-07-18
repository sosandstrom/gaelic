/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.oauth.provider.service;

import com.wadpam.gaelic.GaelicServlet;
import com.wadpam.gaelic.oauth.provider.dao.Do2pProfileDao;
import com.wadpam.gaelic.oauth.provider.domain.Do2pProfile;
import java.util.UUID;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author sosandstrom
 */
public class ProviderService {
    public static final String COOKIE_SIGNIN = "_gaelic_signin";
    public static final String PARAM_USERNAME = "username";
    public static final String PARAM_PASSWORD = "password";
    
    public static final int ERR_BASE = GaelicServlet.ERROR_CODE_OAUTH2_PROVIDER_BASE;
    public static final int ERR_MISSING_REDIRECT_URI = ERR_BASE;
    
    private Do2pProfileDao profileDao;

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

    public String getImplicitToken(String clientId, String redirectUri, Do2pProfile do2pProfile) {
        UUID uuid = UUID.randomUUID();
        final String accessToken = uuid.toString();
        
        return accessToken;
    }

    public void setProfileDao(Do2pProfileDao profileDao) {
        this.profileDao = profileDao;
    }

}
