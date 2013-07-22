/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.tree;

import com.google.appengine.api.NamespaceManager;
import com.wadpam.gaelic.appengine.DomainNamespaceFilter;
import com.wadpam.gaelic.security.DomainSecurityInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sosandstrom
 */
public class DefaultDomainSecurityInterceptor extends DomainSecurityInterceptor {

    @Override
    public String isAuthenticated(HttpServletRequest request, HttpServletResponse response, Object handler, String uri, String method, String authValue) {
        final String currentNamespace = NamespaceManager.get();
        try {
            NamespaceManager.set(null);
            return super.isAuthenticated(request, response, handler, uri, method, authValue); //To change body of generated methods, choose Tools | Templates.
        }
        finally {
            NamespaceManager.set(currentNamespace);
        }
    }
    
}
