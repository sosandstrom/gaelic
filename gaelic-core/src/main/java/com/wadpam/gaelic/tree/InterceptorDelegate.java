/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.tree;

import com.wadpam.gaelic.GaelicServlet;
import com.wadpam.gaelic.Node;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sosandstrom
 */
public class InterceptorDelegate extends NodeDelegate {
    
    private Interceptor interceptor;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Node handler = (Node) req.getAttribute(GaelicServlet.REQUEST_ATTR_HANDLERNODE);
        final boolean proceed = interceptor.preHandle(req, resp, handler);
            
        if (proceed) {
            enqueueForCompletion(req, interceptor);
            super.service(req, resp);

            interceptor.postHandle(req, resp, handler, req);
        }
        else if (null == req.getAttribute(GaelicServlet.REQUEST_ATTR_RESPONSEBODY)) {
            setResponseBody(req, 403, null);
        }
    };

    protected static void enqueueForCompletion(HttpServletRequest req, Interceptor interceptor) {
        ArrayList<Interceptor> interceptors = (ArrayList<Interceptor>) req.getAttribute(GaelicServlet.REQUEST_ATTR_INTERCEPTORS);
        if (null == interceptors) {
            interceptors = new ArrayList<Interceptor>();
            req.setAttribute(GaelicServlet.REQUEST_ATTR_INTERCEPTORS, interceptors);
        }
        interceptors.add(interceptor);
    }

    public void setInterceptor(Interceptor interceptor) {
        this.interceptor = interceptor;
    }

}
