/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic;

import java.io.IOException;
import java.util.LinkedList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sosandstrom
 */
public class Node extends HttpServlet {
    
    public static final String METHOD_DELETE = "DELETE";
    public static final String METHOD_GET = "GET";
    public static final String METHOD_POST = "POST";
    
    public static final String PATH_DOMAIN = "{domain}";
    
    protected static final Logger LOG = LoggerFactory.getLogger(Node.class);
    
    private String name = null;
    protected Node parent = null;
    protected static final ThreadLocal<HttpServletRequest> currentRequest = 
            new ThreadLocal<HttpServletRequest>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        
        initNode(config, null);
    }
    
    public void initNode(ServletConfig config, Node parent) throws ServletException {
        LOG.debug("Initializing node {}", toString());
    }

    protected static void forward(HttpServletRequest request, HttpServletResponse response,
            String forwardPath) throws ServletException, IOException {
        final RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
        dispatcher.forward(request, response);
    }
    
    public Node getServingNode(HttpServletRequest request, 
            LinkedList<String> pathList,
            int pathIndex) {
        currentRequest.set(request);
        return this;
    }
    
    protected static void redirect(HttpServletRequest request, HttpServletResponse response, 
            String redirectPath) throws ServletException, IOException {
        response.sendRedirect(redirectPath);
    }
    
    protected void setResponseBody(HttpServletRequest request, Integer status, Object body) {
        request.setAttribute(GaelicServlet.REQUEST_ATTR_RESPONSEBODY, body);
        if (null != status) {
            request.setAttribute(GaelicServlet.REQUEST_ATTR_RESPONSESTATUS, status);
        }
    }

    @Override
    public String toString() {
        return String.format("%s.%s", getClass().getSimpleName(), name);
    }
    
    public String getName() {
        return name;
    }

    public void setName(String nodeName) {
        this.name = nodeName;
    }
    
    public String getDomain() {
        return getPathVariable(PATH_DOMAIN);
    }
    
    public static String getPathVariableKey(String name) {
        return String.format("com.wadpam.gaelic.PathVariable.%s", name);
    }
    
    public String getPathVariable(String name) {
        return (String) currentRequest.get().getAttribute(getPathVariableKey(name));
    }
    
    public void setPathVariable(String name, String value) {
        currentRequest.get().setAttribute(getPathVariableKey(name), value);
    }
    
}
