/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.security;

import com.wadpam.gaelic.Node;
import com.wadpam.gaelic.exception.RestException;
import com.wadpam.gaelic.tree.InterceptorAdapter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sosandstrom
 */
public class RolesInterceptor extends InterceptorAdapter {
    
    static final Logger LOG = LoggerFactory.getLogger(RolesInterceptor.class);

    // Paths
    private final ArrayList<Map.Entry<Pattern, Set<String>>> RULED_METHODS = 
            new ArrayList<Map.Entry<Pattern, Set<String>>>();
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
            Node handler) throws IOException, ServletException {
        
        Object principal = request.getAttribute(SecurityInterceptor.ATTR_NAME_PRINCIPAL);
        if (null != principal) {
            
            // this will be empty list, never null
            final Collection<String> grantedRoles = (Collection<String>) request.getAttribute(SecurityInterceptor.ATTR_NAME_ROLES);
            
            // build the method:path String
            final String method = request.getMethod();
            final String requestUri = request.getRequestURI();
            
            final boolean authorized = isAuthorized(RULED_METHODS, method, requestUri, grantedRoles);
            if (authorized) {
                return true;
            }
        }
        
        response.sendError(RestException.STATUS_FORBIDDEN, "Forbidden");
        return false;
    }

    protected static boolean isAuthorized(ArrayList<Map.Entry<Pattern, Set<String>>> RULED_METHODS, String method, String requestUri, Collection<String> grantedRoles) {
        final String methodPath = String.format("%s:%s", method, requestUri);
        LOG.debug("method:requestUri = {}, Granted Roles: {}", methodPath, grantedRoles);

        // iterate the list top-down to find matching method:path
        Matcher matcher;
        boolean matches;
        for (Map.Entry<Pattern, Set<String>> entry : RULED_METHODS) {
            matcher = entry.getKey().matcher(methodPath);
            matches = matcher.find();
            LOG.debug("-   {} for {}", matches, entry.getKey());

            if (matches) {
                final Set<String> authorizedRoles = entry.getValue();
                LOG.debug("-   Authorized Roles: {}", authorizedRoles);

                // disjoint is true if the two specified collections have no elements in common.
                boolean disjoint = Collections.disjoint(grantedRoles, authorizedRoles);
                if (!disjoint) {

                    // at least one role in common means Authorized:
                    return true;
                }

                // match && no elements in common - Forbidden
                break;
            }
        }
        return false;
    }
    
    public void setRuledMethods(Collection<Map.Entry<String, Collection<String>>> ruledMethods) {
        SecurityInterceptor.setListedMethods(ruledMethods, RULED_METHODS);
    }
}

