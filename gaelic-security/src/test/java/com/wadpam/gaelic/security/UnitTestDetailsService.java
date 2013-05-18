/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.security;

import static com.wadpam.gaelic.security.SecurityDetailsService.ROLE_APPLICATION;
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
    public SecurityDetails loadUserDetailsByUsername(HttpServletRequest request, HttpServletResponse response, String uri, String authValue, Object userKey) {
        if ("username".equals(userKey)) {
            return BUILDER.with("username")
                    .password("password")
                    .roles(Arrays.asList(ROLE_APPLICATION))
                    .build();
        }
        return null;
    }

}
