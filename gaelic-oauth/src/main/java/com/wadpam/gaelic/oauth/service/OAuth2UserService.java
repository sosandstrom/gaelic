/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.oauth.service;

import com.wadpam.gaelic.security.SecurityDetailsService;

/**
 *
 * @author sosandstrom
 */
public interface OAuth2UserService extends SecurityDetailsService {

    /**
     * Creates a new user based on Connection details
     * @param email
     * @param firstName
     * @param lastName
     * @param name
     * @param providerId 
     * @param providerUserId
     * @return the ID for the created user
     */
    String createUser(String email, String firstName, String lastName, 
            String name, String providerId, String providerUserId, String domain);
}
