/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.oauth.provider.tree;

import com.wadpam.gaelic.Node;
import com.wadpam.gaelic.exception.BadRequestException;
import com.wadpam.gaelic.oauth.provider.domain.Do2pClient;
import com.wadpam.gaelic.oauth.provider.json.JAccessTokenResponse;
import com.wadpam.gaelic.oauth.provider.service.ClientService;
import com.wadpam.gaelic.oauth.provider.service.ProviderService;
import static com.wadpam.gaelic.oauth.provider.tree.AuthorizeEndpointLeaf.PARAM_CLIENT_ID;
import static com.wadpam.gaelic.oauth.provider.tree.AuthorizeEndpointLeaf.PARAM_REDIRECT_URI;
import static com.wadpam.gaelic.oauth.provider.tree.AuthorizeEndpointLeaf.PARAM_RESPONSE_TYPE;
import static com.wadpam.gaelic.oauth.provider.tree.AuthorizeEndpointLeaf.PARAM_STATE;
import com.wadpam.gaelic.security.SecurityInterceptor;
import java.io.IOException;
import java.util.TreeMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sosandstrom
 */
public class TokenEndpointLeaf extends Node {
    
    public static final String GRANT_TYPE_AUTHORIZATION_CODE = "authorization_code";
    public static final String GRANT_TYPE_REFRESH_TOKEN = "refresh_token";
    public static final String PARAM_GRANT_TYPE = "grant_type";
    public static final String PARAM_REFRESH_TOKEN = GRANT_TYPE_REFRESH_TOKEN;

    private ProviderService providerService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final JAccessTokenResponse body = new JAccessTokenResponse();
        final String state = request.getParameter(PARAM_STATE);
        if (null != state) {
            body.setState(state);
        }
        
        final Do2pClient client = (Do2pClient) request.getAttribute(ClientService.ATTR_CLIENT);
        if (null != client) {
        
            final String grantType = request.getParameter(PARAM_GRANT_TYPE);
            
            if (GRANT_TYPE_AUTHORIZATION_CODE.equals(grantType)) {
                
                final String redirectUri = request.getParameter(PARAM_REDIRECT_URI);
                if (null == redirectUri) {
                    throw new BadRequestException(ProviderService.ERR_MISSING_REDIRECT_URI, "Missing redirect_uri", "http://tools.ietf.org/html/rfc6749#section-4");
                }

                final String code = request.getParameter(AuthorizeEndpointLeaf.PARAM_CODE);
                providerService.exchangeCodeForToken(code, redirectUri, client, body);
                if (null != body.getAccess_token()) {
                    setResponseBody(request, HttpServletResponse.SC_OK, body);
                }
                else {
                    TreeMap<String, Object> error = new TreeMap<String, Object>();
                    error.put("error", "invalid_grant");
                    setResponseBody(request, HttpServletResponse.SC_BAD_REQUEST, error);
                }
            }
            else if (GRANT_TYPE_REFRESH_TOKEN.equals(grantType)) {
                final String refreshToken = request.getParameter(PARAM_REFRESH_TOKEN);
                providerService.refreshToken(refreshToken, client, body);
                if (null != body.getAccess_token()) {
                    setResponseBody(request, HttpServletResponse.SC_OK, body);
                }
                else {
                    TreeMap<String, Object> error = new TreeMap<String, Object>();
                    error.put("error", "invalid_grant");
                    setResponseBody(request, HttpServletResponse.SC_BAD_REQUEST, error);
                }
            }
            else {
                TreeMap<String, Object> error = new TreeMap<String, Object>();
                error.put("error", "unsupported_grant_type");
                setResponseBody(request, HttpServletResponse.SC_BAD_REQUEST, error);
            }
        }
        else {
            TreeMap<String, Object> error = new TreeMap<String, Object>();
            error.put("error", "invalid_client");
            setResponseBody(request, HttpServletResponse.SC_BAD_REQUEST, error);
        }
    }
    

    public void setProviderService(ProviderService providerService) {
        this.providerService = providerService;
    }
    
}
