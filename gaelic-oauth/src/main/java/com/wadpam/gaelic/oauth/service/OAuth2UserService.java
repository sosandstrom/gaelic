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
public interface OAuth2UserService<T extends DOAuth2User> extends CrudService<T> {

    /**
     * Creates a new user based on Connection details
     * @param email
     * @param firstName
     * @param lastName
     * @param name
     * @param providerId 
     * @param providerUserId
     * @return the created user
     */
    T createUser(String email, String firstName, String lastName, 
            String name, String providerId, String providerUserId, 
            String username, String profileUrl);
    
    /**
     * @param user
     * @return the primary key for the specified user
     */
    Object getUserKey(T user);
}
