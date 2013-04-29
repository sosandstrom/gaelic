/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.tree;

import com.wadpam.gaelic.Node;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sosandstrom
 */
public interface Interceptor {
    
    boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Node handler) throws Exception;
    
    void postHandle(HttpServletRequest request, HttpServletResponse response,
            Node handler, Object model) throws Exception;
    
    void afterCompletion(HttpServletRequest request, HttpServletResponse response,
            Node handler, Exception ex) throws Exception;
    
}
