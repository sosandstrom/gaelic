/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sosandstrom
 */
public interface SecurityDetailsService {

    /** 
     * Role given if method:path is whitelisted.
     */
    public static final String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";
    
    /** 
     * Role given if application is authenticated, 
     * usually via Basic Authentication.
     */
    public static final String ROLE_APPLICATION = "ROLE_APPLICATION";
    
    /** 
     * Role given if user is authenticated, 
     * usually via Basic Authentication or OAuth2.
     */
    public static final String ROLE_USER = "ROLE_USER";
    
    /**
     * Loads the user details for the specified username.
     * @param request
     * @param response
     * @param uri
     * @param authValue
     * @param username
     * @return the user details for the specified username.
     */
    SecurityDetails loadUserDetailsByUsername(HttpServletRequest request, 
            HttpServletResponse response, 
            String uri, 
            String authValue, 
            Object userKey);

    SecurityDetailsBuilder BUILDER = new SecurityDetailsBuilder();
}
