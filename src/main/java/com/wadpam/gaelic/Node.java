/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic;

import java.util.LinkedList;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sosandstrom
 */
public class Node extends HttpServlet {
    
    protected static final Logger LOG = LoggerFactory.getLogger(Node.class);
    
    private String name = null;
    protected Node parent = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        
        initNode(config, null);
    }
    
    public void initNode(ServletConfig config, Node parent) throws ServletException {
        LOG.debug("Initializing node {}", toString());
    }
    
    public Node getServingNode(HttpServletRequest request, 
            LinkedList<String> pathList,
            int pathIndex) {
        return this;
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
    
}
