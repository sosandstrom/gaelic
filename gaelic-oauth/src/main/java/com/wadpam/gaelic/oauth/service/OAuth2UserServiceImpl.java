/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.oauth.service;

import com.wadpam.gaelic.crud.MardaoCrudService;
import com.wadpam.gaelic.oauth.dao.DOAuth2UserDao;
import com.wadpam.gaelic.oauth.dao.DOAuth2UserDaoBean;
import com.wadpam.gaelic.oauth.domain.DOAuth2User;
import com.wadpam.gaelic.security.SecurityDetailsService;
import java.util.ArrayList;
import net.sf.mardao.core.dao.Dao;


/**
 *
 * @author sosandstrom
 */
public class OAuth2UserServiceImpl<T extends DOAuth2User, D extends Dao<T, Long>> 
        extends MardaoCrudService<T, Long, D>
        implements OAuth2UserService<T> {

    public OAuth2UserServiceImpl() {
        super(DOAuth2User.class, Long.class, DOAuth2UserDaoBean.class);
    }

    public OAuth2UserServiceImpl(Class domainClass, Class daoClass) {
        super(domainClass, Long.class, daoClass);
    }
    
    @Override
    public T createUser(String email, String firstName, String lastName, 
            String name, String providerId, String providerUserId, 
            String username, String profileUrl) {
        T user = createDomain();
        user.setDisplayName(name);
        user.setEmail(email);
        ArrayList roles = new ArrayList();
        roles.add(SecurityDetailsService.ROLE_USER);
        final String role = String.format("ROLE_%s", providerId.toUpperCase());
        roles.add(role);
        user.setRoles(roles);
        user.setUsername(username);
        user.setProfileLink(profileUrl);
        create(user);
        return user;
    }    

    @Override
    public Object getUserKey(T user) {
        return getPrimaryKey(user);
    }
}
