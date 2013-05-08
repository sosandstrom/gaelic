/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sosandstrom
 */
public class UnitTestDetailsService implements SecurityDetailsService {

    @Override
    public Object loadUserDetailsByUsername(HttpServletRequest request, HttpServletResponse response, String uri, String authValue, String username) {
        if ("username".equals(username)) {
            return "password";
        }
        return null;
    }

    @Override
    public Collection<String> getRolesFromUserDetails(Object details) {
        if ("password".equals(details)) {
            Arrays.asList(ROLE_APPLICATION);
        }
        return Collections.EMPTY_LIST;
    }

}
