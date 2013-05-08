/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.security;

/**
 *
 * @author sosandstrom
 */
public interface SecurityConfig {
    
    String DELETE = "DELETE";
    String GET = "GET";
    String OPTIONS = "OPTIONS";
    String PATCH = "PATCH";
    String POST = "POST";
    String PUT = "PUT";

    WhitelistBuilder WHITELIST_BUILDER = new WhitelistBuilder();
}
