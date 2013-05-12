/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.oauth.web;

import com.wadpam.gaelic.Node;
import com.wadpam.gaelic.crud.CrudListener;
import com.wadpam.gaelic.crud.CrudObservable;
import com.wadpam.gaelic.json.RestResponse;
import com.wadpam.gaelic.oauth.domain.DConnection;
import com.wadpam.gaelic.oauth.json.JConnection;
import com.wadpam.gaelic.oauth.service.OAuth2Service;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Provides methods to register and unregister a federated OAuth2 access token.
 * @author sosandstrom
 */
public class OAuth2Leaf extends Node implements CrudObservable {
    
    private OAuth2Service service;
    
//    private final CookieGenerator COOKIE_GENERATOR;
    
    private boolean supportCookie = true;

    protected final ArrayList<CrudListener> listeners = new ArrayList<CrudListener>();
    
    public OAuth2Leaf() {
        super();
        // set cookie name to access_token
//        COOKIE_GENERATOR = new CookieGenerator();
//        COOKIE_GENERATOR.setCookieName(SecurityInterceptor.AUTH_PARAM_OAUTH);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        unregisterFederated(req, resp);
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        registerFederated(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        registerFederated(req, resp);
    }
    
    
    /**
     * Registers an access token from a separate (federated) OAuth2 Provider.
     * @param response for adding cookie
     * @param domain for multi-tenancy
     * @param access_token the access token to register
     * @param providerId id of the OAuth2 provider
     * @param providerUserId user's id at the OAuth2 provider (optional since 1.5)
     * @param secret only used for twitter
     * @param expires_in seconds this access token is valid (from now)
     * @param appArg0 provider-specific. For Salesforce, this is instance_url
     * @return the JConnection, containing the user's id in this client app domain.
     */
    public void registerFederated(
            HttpServletRequest request,
            HttpServletResponse response
            ) {
        final String domain = getDomain();
        final String providerId = request.getParameter("providerId");
        final String providerUserId = request.getParameter("providerUserId");
        final String access_token = request.getParameter("access_token");
        final String secret = request.getParameter("secret");
        final String expiresInString = request.getParameter("expires_in");
        final Integer expires_in = null != expiresInString ? Integer.parseInt(expiresInString) : 3600;
        final String appArg0 = request.getParameter("appArg0");
        
        // notify listeners
        preService(request, domain, OAuth2Service.OPERATION_REGISTER_FEDERATED, providerUserId, providerId, access_token);
        
        RestResponse<DConnection> res = service.registerFederated(access_token, 
                providerId, providerUserId, 
                secret, expires_in, appArg0,
                domain);
        
        // set a cookie if supported
        if (supportCookie) {
//            synchronized (COOKIE_GENERATOR) {
//                COOKIE_GENERATOR.setCookiePath(String.format("/api/%s", domain));
//                COOKIE_GENERATOR.setCookieMaxAge(expires_in);
//                COOKIE_GENERATOR.addCookie(response, access_token);
//            }
        }
        
        final JConnection body = new JConnection();
        ConnectionConverter.convertDConnection(res.getBody(), body);
        
        // notify listeners
        postService(request, domain, OAuth2Service.OPERATION_REGISTER_FEDERATED, body, body.getUserId(), res.getBody());
        
        setResponseBody(request, res.getStatus(), body);
    }

    /**
     * Removes the cookie for this host and path.
     * @param response
     * @param domain used to construct the cookie path
     * @param providerId not used.
     * @return 200 on everything
     */
    public void unregisterFederated(
            HttpServletRequest request,
            HttpServletResponse response
//            @PathVariable String domain,
//            @PathVariable String providerId
                    ) {
        
        // delete the cookie client-side:
//        synchronized (COOKIE_GENERATOR) {
//            COOKIE_GENERATOR.setCookiePath(String.format("/api/%s", domain));
//            COOKIE_GENERATOR.removeCookie(response);
//        }
    }

    @Override
    public void addListener(CrudListener listener) {
        listeners.add(listener);
    }
    
    @Override
    public void removeListener(CrudListener listener) {
        listeners.remove(listener);
    }
    
    protected void preService(HttpServletRequest request, String namespace,
            int operation, Object json, Object domain, Serializable id) {
        for (CrudListener l : listeners) {
            l.preService(null, null, request, namespace, 
                    operation, json, domain, id);
        }
    }
    
    protected void postService(HttpServletRequest request, String namespace,
            int operation, Object json, Serializable id, Object serviceResponse) {
        for (CrudListener l : listeners) {
            l.postService(null, null, request, namespace, 
                    operation, json, id, serviceResponse);
        }
    }

    public void setService(OAuth2Service oauth2Service) {
        this.service = oauth2Service;
    }

    public boolean isSupportCookie() {
        return supportCookie;
    }

    public void setSupportCookie(boolean supportCookie) {
        this.supportCookie = supportCookie;
    }
    
}
