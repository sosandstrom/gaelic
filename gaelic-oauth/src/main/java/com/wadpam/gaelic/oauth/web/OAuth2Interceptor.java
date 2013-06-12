/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.oauth.web;

import com.wadpam.gaelic.appengine.DomainNamespaceFilter;
import com.wadpam.gaelic.exception.ForbiddenException;
import com.wadpam.gaelic.exception.NotFoundException;
import com.wadpam.gaelic.exception.RestException;
import com.wadpam.gaelic.json.RestResponse;
import com.wadpam.gaelic.oauth.domain.DConnection;
import com.wadpam.gaelic.oauth.service.ConnectionService;
import com.wadpam.gaelic.oauth.service.OAuth2Service;
import com.wadpam.gaelic.security.DomainSecurityInterceptor;
import com.wadpam.gaelic.security.SecurityDetails;
import com.wadpam.gaelic.security.SecurityDetailsService;
import static com.wadpam.gaelic.security.SecurityInterceptor.ATTR_NAME_PRINCIPAL;
import static com.wadpam.gaelic.security.SecurityInterceptor.ATTR_NAME_USERNAME;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sosandstrom
 */
public class OAuth2Interceptor extends DomainSecurityInterceptor implements SecurityDetailsService {
    
    private boolean autoRegister = true;
    private boolean verifyLocally = true;
    private boolean verifyRemotely = false;
    private String providerId = OAuth2Service.PROVIDER_ID_FACEBOOK;
    
    private ConnectionService connectionService;
    private OAuth2Service oauth2Service = null;
    
    public OAuth2Interceptor() {
        super();
        setAuthenticationMechanism(AUTH_TYPE_OAUTH);
        setSecurityDetailsService(this);
    }

//    @Override
//    protected String getRealmUsername(String clientUsername, Object details) {
//        final DConnection conn = (DConnection) details;
//        Long userId = connectionService.getSimpleKey(conn);
//        return null != userId ? userId.toString() : null;
//    }

    @Override
    public String isAuthenticated(HttpServletRequest request, HttpServletResponse response, Object handler, String uri, String method, String authValue) {
        
        // already registered (local check)?
        String username = super.isAuthenticated(request, response, handler, uri, method, authValue);
        
        // is this a different token, to be registered on-the-fly?
        if (null == username && autoRegister && null != oauth2Service && null != request) {
            
            // does request contain necessary parameters?
            String providerId = request.getParameter("providerId");
            String providerUserId = request.getParameter("providerUserId");
            String secret = request.getParameter("secret");
            String expiresIn = request.getParameter("expires_in");
            Integer expiresInSeconds = null != expiresIn ? Integer.parseInt(expiresIn) : 3600;
            String appArg0 = request.getParameter("appArg0");
            String domain = DomainNamespaceFilter.getDomain();
            
            if (null != providerId && null != expiresInSeconds) {
                
                // register and verify with federated provider
                RestResponse<DConnection> res = oauth2Service.registerFederated(
                        authValue, providerId, providerUserId, secret, expiresInSeconds, appArg0, domain);

                // if it looks good, try to authenticate again, to have all populated:
                if (null != res && null != res.getBody()) {
                    username = super.isAuthenticated(request, response, handler, uri, method, authValue);
                    LOG.info("auto registered: {} for {}", authValue, username);
                }
            }
        }
        
        // the SecurityInterceptor.preHandle will simply return false,
        // which leads to an empty 200 response.
        if (null == username) {
            throw new ForbiddenException(77403, authValue, null);
        }
        
        // replace username being access_token with parentKeyString
        Object principal = request.getAttribute(ATTR_NAME_PRINCIPAL);
        LOG.debug("principal is {}", principal);
        if (principal instanceof DConnection) {
            DConnection conn = (DConnection) principal;
            if (null != conn && null != conn.getUserKey()) {
                final Long userId = oauth2Service.getUserId(conn.getUserKey());
                request.setAttribute(ATTR_NAME_USERNAME, userId.toString());
            }
        }
        
        return username;
    }

    @Override
    public SecurityDetails loadUserDetailsByUsername(HttpServletRequest request, 
            HttpServletResponse response, 
            String uri, 
            String authValue, 
            Object userKey) {
        try {
            final Object realmUsername = verifyAccessToken(authValue, request);
            if (null != realmUsername && null != request) {
                final DConnection conn = (DConnection) request.getAttribute(AUTH_PARAM_OAUTH);
                return conn;
            }
        }
        catch (RestException whenMissing) {
            LOG.info("No token/user found for {}, reason {}", authValue, whenMissing.getMessage());
        }
        return null;
    }

    protected Object verifyAccessToken(String accessToken, HttpServletRequest request) {

        // missing means Unauthorized
        if (null != accessToken) {
            
            // no verification at all?
            if (!verifyLocally && !verifyRemotely) {
                return USERNAME_ANONYMOUS;
            }
            
            DConnection conn = null;
            
            // only verify in local database if configured
            if (verifyLocally) {
                conn = connectionService.findByAccessToken(accessToken);
                if (null == conn) {
                    throw new NotFoundException(403, "No token found in realm", null);
                }
                
                // check expired locally?
                if (!verifyRemotely && null != conn.getExpireTime() && conn.getExpireTime().before(new Date())) {
                    throw new RestException(410, null != conn.getExpireTime() ? conn.getExpireTime().toString() : "No expireTime", 
                            RestException.STATUS_FORBIDDEN, "Authentication expired", null);
                }
            }
            
            // verify remotely?
            if (verifyRemotely) {
                throw new UnsupportedOperationException("Remote verification.");
//                if (null == conn) {
//                    throw new UnsupportedOperationException("For remote verification, local must be enabled too.");
//                }
//                
//                String providerUserId = oauth2Service.getProviderUserId(accessToken, providerId, null);
//                if (null == providerUserId) {
//                    return null;
//                }
//                
//                // double-check userId
//                if (!providerUserId.equals(conn.getProviderUserId())) {
//                    throw new RestException(409, "providerUserId mismatch", null, HttpStatus.FORBIDDEN, "Authentication mismatch");
//                }
            }
            
            if(null != request) {
                request.setAttribute(AUTH_PARAM_OAUTH, conn);
            }
            return conn.getUserKey();
        }

        throw new ForbiddenException(401, "No token found in request", null);
    }

    public void setAutoRegister(boolean autoRegister) {
        this.autoRegister = autoRegister;
    }

    public void setVerifyLocally(boolean verifyLocally) {
        this.verifyLocally = verifyLocally;
    }

    public void setVerifyRemotely(boolean verifyRemotely) {
        this.verifyRemotely = verifyRemotely;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public void setConnectionService(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    public void setOauth2Service(OAuth2Service oauth2Service) {
        this.oauth2Service = oauth2Service;
    }
    
}
