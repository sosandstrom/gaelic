/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.SimpleTimeZone;
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
    public static final String METHOD_PATCH = "PATCH";
    public static final String METHOD_POST = "POST";
    public static final String METHOD_PUT = "PUT";
    
    public static final String PATH_DOMAIN = "{domain}";
    
    public static final Date DATE_ORIGIN = new Date(0L);
    
    /** Tue, 15 Jan 2013 21:47:38 GMT */
    public static final SimpleDateFormat SDF = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss 'GMT'");
    
    public static final String ATTR_NAME_USERNAME = "com.wadpam.open.security.username";
    
    protected static final Logger LOG = LoggerFactory.getLogger(Node.class);
    
    private String name = null;
    protected Node parent = null;
    protected static final ThreadLocal<HttpServletRequest> currentRequest = 
            new ThreadLocal<HttpServletRequest>();
    
    static {
        SDF.setTimeZone(new SimpleTimeZone(0, "GMT"));
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        
        try {
            initNode(config, null);
        }
        catch (IOException ex) {
            throw new ServletException("initNode", ex);
        }
    }
    
    public void initNode(ServletConfig config, Node parent) throws ServletException, IOException {
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
        LOG.debug("redirecting to Location: {}", redirectPath);
        setResponseBody(request, HttpServletResponse.SC_FOUND, null);
        response.sendRedirect(redirectPath);
    }

    protected String deleteCookie(HttpServletResponse response,
            String name,
            String domain,
            String path) {
        return setCookie(response, name, "", null, DATE_ORIGIN, domain, path);
    }
    
    /**
     * Sets a cookie on specified response
     * @param response
     * @param name
     * @param value
     * @param maxAge seconds into the future
     * @param expires rfc1123-date
     * @param domain domain-value
     * @param path path-value
     * @return the Set-Cookie header value
     */
    protected String setCookie(HttpServletResponse response,
            String name,
            String value,
            Integer maxAge,
            Date expires,
            String domain,
            String path) {
        
        StringBuffer sb = new StringBuffer();
        sb.append(String.format("%s=%s", name, value));
        
        if (null != maxAge) {
            sb.append("; Max-Age=");
            sb.append(maxAge);
        }
        
        if (null != expires) {
            sb.append("; Expires=");
            sb.append(SDF.format(expires));
        }
        
        if (null != domain) {
            sb.append("; Domain=");
            sb.append(domain);
        }
        
        if (null != path) {
            sb.append("; Path=");
            sb.append(path);
        }
        
        if (null != response) {
            response.setHeader("Set-Cookie", sb.toString());
        }
        
        return sb.toString();
    }
    
    protected static void setResponseBody(HttpServletRequest request, Integer status, Object body) {
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
    
    public static String getCursorKey(HttpServletRequest request) {
        return request.getParameter("cursorKey");
    }
    
    public static String getDomain() {
        return getPathVariable(PATH_DOMAIN);
    }
    
    public static int getPageSize(HttpServletRequest request) {
        return getPageSize(request, 10);
    }
    
    public static int getPageSize(HttpServletRequest request, int defaultSize) {
        final String s = request.getParameter("pageSize");
        return null != s ? Integer.parseInt(s) : defaultSize;
    }
    
    public static String getPathVariableKey(String name) {
        return String.format("com.wadpam.gaelic.PathVariable.%s", name);
    }
    
    public static String getPathVariable(String name) {
        return (String) currentRequest.get().getAttribute(getPathVariableKey(name));
    }
    
    public static Long getPathVariableLong(String name) {
        final String value = getPathVariable(name);
        return null != value ? Long.parseLong(value) : null;
    }
    
    public void setPathVariable(String name, String value) {
        LOG.trace("{} = {}", name, value);
        currentRequest.get().setAttribute(getPathVariableKey(name), value);
    }
    
    public String getCurrentUsername() {
        return (String) currentRequest.get().getAttribute(ATTR_NAME_USERNAME);
    }
}
