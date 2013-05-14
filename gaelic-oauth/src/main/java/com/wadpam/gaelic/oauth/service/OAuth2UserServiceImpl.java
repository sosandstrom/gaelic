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
import java.util.Collection;
import java.util.Collections;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sosandstrom
 */
public class OAuth2UserServiceImpl 
        extends MardaoCrudService<DOAuth2User, Long, DOAuth2UserDao>
        implements OAuth2UserService, SecurityDetailsService {

    public OAuth2UserServiceImpl() {
        super(DOAuth2User.class, Long.class, DOAuth2UserDaoBean.class);
    }
    
    @Override
    public Object createUser(String email, String firstName, String lastName, 
            String name, String providerId, String providerUserId, 
            String username, String profileUrl) {
        DOAuth2User user = createDomain();
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
        return getPrimaryKey(user);
    }

    @Override
    public Collection<String> getRolesFromUserDetails(Object details) {
        return null != details ? ((DOAuth2User)details).getRoles() : Collections.EMPTY_LIST;
    }

    @Override
    public Object loadUserDetailsByUsername(HttpServletRequest request, HttpServletResponse response, 
            String uri, String authValue, Object userKey) {
        
        final Long userId = dao.getSimpleKeyByPrimaryKey(userKey);
        return get(null, userId);
    }

}
