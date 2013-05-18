/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.oauth.service;

import com.wadpam.gaelic.crud.CrudService;
import com.wadpam.gaelic.oauth.domain.DOAuth2User;

/**
 *
 * @author sosandstrom
 */
public interface OAuth2UserService extends CrudService<DOAuth2User, Long> {

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
    Object createUser(String email, String firstName, String lastName, 
            String name, String providerId, String providerUserId, 
            String username, String profileUrl);
}
