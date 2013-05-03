/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.tree;

import com.wadpam.gaelic.GaelicServlet;
import com.wadpam.gaelic.Node;
import com.wadpam.gaelic.exception.BadRequestException;
import com.wadpam.gaelic.json.JCursorPage;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sosandstrom
 */
public class CrudLeaf<J extends Object, T extends Object, ID extends Serializable> extends Node {
    
    public static final String REQUEST_ATTR_FILENAME = "com.wadpam.gaelic.CrudFilename";
    
    private static final TreeSet<String> SUPPORTED_METHODS = new TreeSet<String>(
            Arrays.asList(METHOD_DELETE, METHOD_GET, METHOD_POST));
    
    private final Class domainClass;
    private final Class idClass;
    private final Class jsonClass;

    public CrudLeaf(Class domainClass, Class idClass, Class jsonClass) {
        this.domainClass = domainClass;
        this.idClass = idClass;
        this.jsonClass = jsonClass;
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // GET details or page?
        final String filename = (String) request.getAttribute(REQUEST_ATTR_FILENAME);
        if (null != filename) {
            getDetails(request, response, filename);
        }
        else {
            getPage(request, response);
        }
    }

    protected void getDetails(HttpServletRequest request, HttpServletResponse response,
            String filename) throws ServletException, IOException {
        
        // if ID is Long, parse filename
        if (Long.class.equals(idClass)) {
            try {
                long id = Long.parseLong(filename);
            }
            catch (NumberFormatException notLong) {
                throw new BadRequestException(GaelicServlet.ERROR_CODE_ID_LONG, filename, null);
            }
        }
        
        // TODO: implement
        setResponseBody(request, 200, filename);
    }
    
    protected void getPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final JCursorPage page = new JCursorPage();
        
        // TODO: implement
        setResponseBody(request, 200, page);
    }
    
    @Override
    public Node getServingNode(HttpServletRequest request, LinkedList<String> pathList, final int pathIndex) {
        final String method = request.getMethod();
        LOG.info("   mapping {} {} for {} ({})", new Object[] {
            method, request.getRequestURI(), pathIndex, pathList.size()
        });
        
        if (supportedMethods().contains(method)) {
            
            // support '' and /
            if (pathIndex == pathList.size() || 
                    (pathIndex == pathList.size()-1 && pathList.getLast().isEmpty())) {
                return this;
            }
            
            // support /{id} and /{id}/ 
            if ((pathIndex == pathList.size()-1) ||
                    (pathIndex == pathList.size()-2 && pathList.getLast().isEmpty())) {
                
                request.setAttribute(REQUEST_ATTR_FILENAME, pathList.get(pathIndex));
                return this;
            }
        }
        
        return null;
    }
    
    /**
     * Override to implement a read-only Leaf.
     * @return the supported HTTP methods for this CrudLeaf
     */
    protected Set<String> supportedMethods() {
        return SUPPORTED_METHODS;
    }
    
}
