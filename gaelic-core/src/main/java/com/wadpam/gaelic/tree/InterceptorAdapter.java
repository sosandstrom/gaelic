/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.tree;

import com.wadpam.gaelic.GaelicServlet;
import com.wadpam.gaelic.Node;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sosandstrom
 */
public class InterceptorAdapter extends AbstractPath implements Interceptor {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Node handler = (Node) req.getAttribute(GaelicServlet.REQUEST_ATTR_HANDLERNODE);
        Exception exception = null;
        boolean proceed = false;
        try {
            proceed = preHandle(req, resp, handler);
            
            if (proceed) {
                super.service(req, resp);
                
                postHandle(req, resp, handler, req);
            }
            else {
                setResponseBody(req, 403, null);
            }
        }
        catch (Exception ex) {
            exception = ex;
        }
        
        if (proceed) {
            try {
                afterCompletion(req, resp, handler, exception);
            } catch (Exception ex) {
                if (null == exception) {
                    exception = ex;
                }
            }
        }
        
        if (null != exception) {
            throw new ServletException(req.getRequestURI(), exception);
        }
    };
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Node handler) throws Exception {
        LOG.trace("{}.preHandle({})", getName(), handler);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Node handler, Object model) throws Exception {
        LOG.trace("{}.postHandle({})", getName(), handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Node handler, Exception ex) throws Exception {
        LOG.trace("{}.afterCompletion({})", getName(), handler);
    }

}
