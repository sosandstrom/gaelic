/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.web;

import com.wadpam.gaelic.Node;
import com.wadpam.gaelic.tree.Interceptor;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.mardao.core.dao.DaoImpl;

/**
 *
 * @author sosandstrom
 */
public class MardaoPrincipalInterceptor implements Interceptor {
    /** Must be same as SecurityInterceptor constant */
    public static final String ATTR_NAME_USERNAME = Node.ATTR_NAME_USERNAME;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Node handler) throws ServletException, IOException {
        final String principalName = (String) request.getAttribute(ATTR_NAME_USERNAME);
        DaoImpl.setPrincipalName(principalName);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Node handler, Object model) throws ServletException, IOException {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Node handler, Exception ex) throws ServletException, IOException {
        DaoImpl.setPrincipalName(null);
    }

}
