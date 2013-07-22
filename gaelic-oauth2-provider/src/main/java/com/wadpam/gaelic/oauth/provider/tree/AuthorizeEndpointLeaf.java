/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.oauth.provider.tree;

import com.wadpam.gaelic.Node;
import com.wadpam.gaelic.exception.BadRequestException;
import com.wadpam.gaelic.net.NetworkTemplate;
import com.wadpam.gaelic.oauth.provider.domain.Do2pClient;
import com.wadpam.gaelic.oauth.provider.domain.Do2pProfile;
import com.wadpam.gaelic.oauth.provider.service.ProviderService;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Implements the Authorization Endpoint.
 * http://tools.ietf.org/html/rfc6749#section-3.1
 * @author sosandstrom
 */
public class AuthorizeEndpointLeaf extends Node {

    public static final String PARAM_ACCESS_TOKEN = "access_token";
    public static final String PARAM_CLIENT_ID = "client_id";
    public static final String PARAM_CODE = "code";
    public static final String PARAM_ERROR = "error";
    public static final String PARAM_EXPIRES_IN = "expires_in";
    public static final String PARAM_REDIRECT_URI = "redirect_uri";
    public static final String PARAM_RESPONSE_TYPE = "response_type";
    public static final String PARAM_STATE = "state";
    public static final String PARAM_TOKEN_TYPE = "token_type";
    
    public static final String RESPONSE_TYPE_CODE = PARAM_CODE;
    public static final String RESPONSE_TYPE_TOKEN = "token";
    public static final String LOGIN_URI_DEFAULT = "login.html";
    
    private ProviderService providerService;
    private String loginUri = LOGIN_URI_DEFAULT;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String redirectUri = request.getParameter(PARAM_REDIRECT_URI);
        if (null == redirectUri) {
            throw new BadRequestException(ProviderService.ERR_MISSING_REDIRECT_URI, "Missing redirect_uri", "http://tools.ietf.org/html/rfc6749#section-4");
        }
        
        HashMap<String, Object> paramMap = new HashMap<String, Object>();

        final String state = request.getParameter(PARAM_STATE);
        if (null != state) {
            paramMap.put(PARAM_STATE, state);
        }
        
        final String responseType = request.getParameter(PARAM_RESPONSE_TYPE);
        final String clientId = request.getParameter(PARAM_CLIENT_ID);
        String separator = NetworkTemplate.SEPARATOR_QUERY;
        
        // required parameters present?
        if (null != clientId && null != responseType) {
            
            // authenticate client
            Do2pClient client = providerService.getClient(clientId);
            if (null != client) {

                // authenticate the user
                Do2pProfile do2pProfile = providerService.authenticate(request);
                if (null == do2pProfile) {
                    paramMap.put(PARAM_REDIRECT_URI, redirectUri);
                    paramMap.put(PARAM_CLIENT_ID, clientId);
                    paramMap.put(PARAM_RESPONSE_TYPE, responseType);

                    redirect(request, response, NetworkTemplate.expandUrl(loginUri, paramMap));
                    return;
                }

                // which response type?
                if (RESPONSE_TYPE_CODE.equals(responseType)) {
                    final String code = providerService.getAuthorizationCode(client, redirectUri, do2pProfile);
                    paramMap.put(PARAM_CODE, code);
                }
                else if (RESPONSE_TYPE_TOKEN.equals(responseType)) {
                    final String accessToken = providerService.getImplicitToken(client, redirectUri, do2pProfile);
                    long expiresInMillis = providerService.getImplicitTTL();
                    separator = NetworkTemplate.SEPARATOR_FRAGMENT;
                    paramMap.put(PARAM_ACCESS_TOKEN, accessToken);
                    paramMap.put(PARAM_EXPIRES_IN, Long.toString(expiresInMillis / 1000L));
                    paramMap.put(PARAM_TOKEN_TYPE, ProviderService.TOKEN_TYPE);
                }
                else {
                    paramMap.put(PARAM_ERROR, "unsupported_response_type");
                }
            }
            else {
                paramMap.put(PARAM_ERROR, "unauthorized_client");
            }
        }
        else {
            paramMap.put(PARAM_ERROR, "invalid_request");
        }

        final String url = NetworkTemplate.expandUrl(redirectUri, separator, paramMap);
        redirect(request, response, url);
    }

    /**
     * Support the Login form POST
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void setProviderService(ProviderService providerService) {
        this.providerService = providerService;
    }
    
}
