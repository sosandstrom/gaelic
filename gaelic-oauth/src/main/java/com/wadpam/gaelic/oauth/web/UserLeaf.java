/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.oauth.web;

import com.wadpam.gaelic.oauth.dao.DOAuth2UserDao;
import com.wadpam.gaelic.oauth.domain.DOAuth2User;
import com.wadpam.gaelic.oauth.json.JOAuth2User;
import com.wadpam.gaelic.oauth.service.OAuth2UserServiceImpl;
import com.wadpam.gaelic.tree.CrudLeaf;

/**
 *
 * @author sosandstrom
 */
public class UserLeaf extends CrudLeaf<JOAuth2User, DOAuth2User, Long, OAuth2UserServiceImpl<DOAuth2User, DOAuth2UserDao>> {

    public UserLeaf() {
        super(DOAuth2User.class, Long.class, JOAuth2User.class);
        converter = new UserConverter<JOAuth2User, DOAuth2User>(JOAuth2User.class, DOAuth2User.class);
    }

}
