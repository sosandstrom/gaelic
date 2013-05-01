/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.tree;

import com.wadpam.gaelic.Node;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sosandstrom
 */
public class UnitTestInterceptor extends InterceptorAdapter {
    
    public static final String REQUEST_ATTR_INTERCEPTOR_PRE = "junit.preHandle";
    public static final String REQUEST_ATTR_INTERCEPTOR_POST = "junit.postHandle";
    public static final String REQUEST_ATTR_INTERCEPTOR_AFTER = "junit.afterCompletion";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Node handler) throws ServletException, IOException {
        final String uri = request.getRequestURI();
        request.setAttribute(REQUEST_ATTR_INTERCEPTOR_PRE, uri);
        return uri.equals("/api/gaelic/interceptor/true");
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Node handler, Object model) throws ServletException, IOException {
        request.setAttribute(REQUEST_ATTR_INTERCEPTOR_POST, request.getRequestURI());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Node handler, Exception ex) throws ServletException, IOException {
        request.setAttribute(REQUEST_ATTR_INTERCEPTOR_AFTER, request.getRequestURI());
    }

}
