/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.tree;

import com.google.appengine.api.NamespaceManager;
import com.wadpam.gaelic.Node;
import com.wadpam.gaelic.appengine.DomainNamespaceFilter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sosandstrom
 */
public class DomainNamespaceInterceptor implements Interceptor {
    
    public static final String ATTR_NAME_DOMAIN_NAMESPACE = "com.wadpam.gaelic.DomainNamespace";
    public static final String ATTR_NAME_ENTRY_NAMESPACE = "com.wadpam.gaelic.EntryNamespace";
    
    static final Logger LOG = LoggerFactory.getLogger(DomainNamespaceInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Node handler) throws ServletException, IOException {
        final String uri = request.getRequestURI();
        final String domain = DomainNamespaceFilter.getDomainNamespace(uri);
        LOG.info("Switching namespace to {}, {} {}", 
                new Object[] {domain, request.getMethod(), uri});
        
        final String entry = NamespaceManager.get();
        request.setAttribute(ATTR_NAME_ENTRY_NAMESPACE, entry);

        NamespaceManager.set(domain);
        request.setAttribute(ATTR_NAME_DOMAIN_NAMESPACE, domain);
        
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Node handler, Object model) throws ServletException, IOException {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Node handler, Exception ex) throws ServletException, IOException {
        final String entry = (String) request.getAttribute(ATTR_NAME_ENTRY_NAMESPACE);
        NamespaceManager.set(entry);
        request.removeAttribute(ATTR_NAME_DOMAIN_NAMESPACE);
    }

    public static String getDomain(HttpServletRequest request) {
        return (String) request.getAttribute(ATTR_NAME_DOMAIN_NAMESPACE);
    }
}
