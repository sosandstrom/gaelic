/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.tree;

import com.wadpam.gaelic.GaelicServlet;
import com.wadpam.gaelic.Node;
import com.wadpam.gaelic.converter.BaseConverter;
import com.wadpam.gaelic.crud.CrudService;
import com.wadpam.gaelic.exception.BadRequestException;
import com.wadpam.gaelic.json.JCursorPage;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
public class CrudLeaf<J extends Serializable, 
        T, 
        ID extends Serializable,
        S extends CrudService<T, ID>> extends Node {
    
    public static final String REQUEST_ATTR_FILENAME = "com.wadpam.gaelic.CrudFilename";
    
    private static final TreeSet<String> SUPPORTED_METHODS = new TreeSet<String>(
            Arrays.asList(METHOD_DELETE, METHOD_GET, METHOD_POST));
    
    private final Class domainClass;
    private final Class idClass;
    private final Class jsonClass;
    
    protected S service;
    
    protected BaseConverter<J, T, ID> converter;

    public CrudLeaf(Class domainClass, Class idClass, Class jsonClass) {
        this.domainClass = domainClass;
        this.idClass = idClass;
        this.jsonClass = jsonClass;
    }
    
    public void addInnerObjects(HttpServletRequest request, 
            HttpServletResponse response,
            String domain,
            J jEntity) {
        addInnerObjects(request, response, domain, 
                Arrays.asList(jEntity));
    }

    /** This implementation does nothing, please override */
    public void addInnerObjects(HttpServletRequest request, 
            HttpServletResponse response,
            String domain,
            Iterable<J> jEntity) {
        // do nothing
    }
    
    // Convert iterable
    public Collection<J> convertWithInner(HttpServletRequest request, HttpServletResponse response,
            String domain, Iterable<T> from) {
        if (null == from)
            return new ArrayList<J>();

        // basic conversion first
        final Collection<J> returnValue = converter.convert(from);

        // then add inner objects batch-style
        addInnerObjects(request, response, domain, returnValue);
        
        return returnValue;
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
        ID id;
        
        // if ID is Long, parse filename
        if (Long.class.equals(idClass)) {
            try {
                Long l = Long.parseLong(filename);
                id = (ID) l;
            }
            catch (NumberFormatException notLong) {
                throw new BadRequestException(GaelicServlet.ERROR_CODE_ID_LONG, filename, null);
            }
        }
        else {
            id = (ID) filename;
        }
        
        // TODO: implement
        T domain = service.get(null, id);
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
    
    protected J convertWithInner(HttpServletRequest request, HttpServletResponse response,
            String domain, T from) {
        final J to = converter.convertDomain(from);
        addInnerObjects(request, response, domain, to);
        return to;
    }
    
    

    public void setService(S service) {
        this.service = service;
    }
}
