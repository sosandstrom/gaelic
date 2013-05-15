/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.oauth.web;

import com.wadpam.gaelic.converter.MardaoConverter;
import com.wadpam.gaelic.oauth.domain.DOAuth2User;
import com.wadpam.gaelic.oauth.json.JOAuth2User;

/**
 *
 * @author sosandstrom
 */
public class UserConverter<JU extends JOAuth2User, D extends DOAuth2User> 
        extends MardaoConverter<JU, D> {

    public UserConverter(Class jsonClass, Class domainClass) {
        super(jsonClass, domainClass);
    }
    
    public UserConverter() {
        this(JOAuth2User.class, DOAuth2User.class);
    }

    @Override
    public void convertDomain(D from, JU to) {
        convertDUser(from, to);
    }

    public static void convertDUser(DOAuth2User from, JOAuth2User to) {
        convertLongEntity(from, to);
        to.setDisplayName(from.getDisplayName());
        to.setEmail(from.getEmail());
        to.setProfileLink(from.getProfileLink());
        to.setRoles(from.getRoles());
        to.setThumbnailUrl(from.getThumbnailUrl());
        to.setUsername(from.getUsername());
    }

    @Override
    public void convertJson(JU from, D to) {
        convertJUser(from, to);
    }

    public static void convertJUser(JOAuth2User from, DOAuth2User to) {
        convertJLong(from, to);
        to.setDisplayName(from.getDisplayName());
        to.setEmail(from.getEmail());
        to.setProfileLink(from.getProfileLink());
        to.setRoles(from.getRoles());
        to.setThumbnailUrl(from.getThumbnailUrl());
        to.setUsername(from.getUsername());
    }
}
