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
public class InterceptorAdapter implements Interceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Node handler) throws ServletException, IOException {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Node handler, Object model) throws ServletException, IOException {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Node handler, Exception ex) throws ServletException, IOException {
    }

}
