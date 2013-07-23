/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.tree;

import com.wadpam.gaelic.GaelicServlet;
import com.wadpam.gaelic.Node;
import com.wadpam.gaelic.converter.BaseConverter;
import com.wadpam.gaelic.crud.CrudService;
import com.wadpam.gaelic.exception.BadRequestException;
import com.wadpam.gaelic.exception.MethodNotAllowedException;
import com.wadpam.gaelic.exception.NotFoundException;
import com.wadpam.gaelic.json.JCursorPage;
import com.wadpam.gaelic.json.JKey;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sosandstrom
 */
public class CrudLeaf<J extends Serializable, T, S extends CrudService<T>> extends LeafAdapter<J> {
    
    public static final int STATUS_OK = 200;
    public static final int STATUS_CREATED = 201;
    
    public static final int ERR_OFFSET_PAGE = 1;
    public static final int ERR_OFFSET_DETAILS = 2;
    public static final int ERR_OFFSET_METHOD = 3;
    public static final int ERR_OFFSET_DELETE_ALL = 4;
    
    public static final String REQUEST_ATTR_FILENAME = "com.wadpam.gaelic.CrudFilename";
    public static final String REQUEST_PARAM_EXPECTS = "_expects";
    
    private static final TreeSet<String> SUPPORTED_METHODS = new TreeSet<String>(
            Arrays.asList(METHOD_DELETE, METHOD_GET, METHOD_POST));
    
    private final Class domainClass;
    private final Class idClass;
    
    protected S service;
    
    protected BaseConverter<J, T> converter;

    public CrudLeaf(Class domainClass, Class idClass, Class jsonClass) {
        super(jsonClass, domainClass.getSimpleName());
        this.domainClass = domainClass;
        this.idClass = idClass;
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
        final Collection<J> returnValue = converter.convertDomains(from);

        // then add inner objects batch-style
        addInnerObjects(request, response, domain, returnValue);
        
        return returnValue;
    }

    @Override
    protected void createResource(HttpServletRequest request, HttpServletResponse response, JKey jKey, J body) throws ServletException, IOException {
        final T domain = converter.convertJson(body);
        
        if (null != domain) {
            final String id = service.create(domain);
            if ("302".equals(request.getParameter(REQUEST_PARAM_EXPECTS))) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
            else {
                final J responseBody = converter.convertDomain(domain);
                setResponseBody(request, STATUS_CREATED, responseBody);
            }
        }
    }
    
    protected void delete(HttpServletRequest request, HttpServletResponse response,
            String id) {
        service.delete(null, id);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String id = getId(request);
        LOG.debug("doDelete {}", id);
        
        // GET details or page?
        if (null != id) {
            delete(request, response, id);
        }
        else {
            throw new BadRequestException(getErrorBaseCode()+ERR_OFFSET_DELETE_ALL, toString(), null);
        }
    }

    @Override
    protected void getResourceByKey(HttpServletRequest request, HttpServletResponse response, JKey jKey) throws ServletException, IOException {

        // schema-based manager forms
        if ("manager.html".equals(jKey.getId())) {
            final String fwdPath = "/internal/bootstrap-schema.html";
            forward(request, response, fwdPath);
            return;
        }
        
        // schema AJAX method
        if ("schema".equals(jKey.getId())) {
            getSchema(request, response);
            return;
        }

        T domain = service.get(jKey.getParentKey(), getId(jKey.getId()));
        if (null != domain) {
            J body = converter.convertDomain(domain);
            setResponseBody(request, 200, body);
        }
        else {
            throw new NotFoundException(getErrorBaseCode()+ERR_OFFSET_DETAILS, toString(), null);
        }
    }
    
    
    
    protected int getErrorBaseCode() {
        return GaelicServlet.ERROR_CODE_CRUD_BASE;
    }
    
    protected String getId(HttpServletRequest request) {
        final String filename = (String) request.getAttribute(REQUEST_ATTR_FILENAME);
        return getId(filename);
    }
    
    protected String getId(final String filename) {
//        ID id = null;
//        
//        if (null != filename) {
//            // if ID is Long, parse filename
//            if (Long.class.equals(idClass)) {
//                try {
//                    Long l = Long.parseLong(filename);
//                    id = (ID) l;
//                }
//                catch (NumberFormatException notLong) {
//                    throw new BadRequestException(GaelicServlet.ERROR_CODE_ID_LONG, filename, null);
//                }
//            }
//            else {
//                id = (ID) filename;
//            }
//        }
//        
//        return id;
        return filename;
    }
    
    @Override
    protected void getResourcesPage(HttpServletRequest request, HttpServletResponse response,
            JKey parentKey, int pageSize, String cursorKey) throws ServletException, IOException {
        
        final JCursorPage<T> page = service.getPage(parentKey, pageSize, cursorKey);
        final JCursorPage<J> body = converter.convertDomainPage(page);
        
        setResponseBody(request, 200, body);
    }
    
    protected void getSchema(HttpServletRequest request, HttpServletResponse response) {
        final TreeMap<String, Object> body = new TreeMap<String, Object>();
        body.put("tableName", service.getTableName());
        body.put("primaryKeyName", service.getPrimaryKeyColumnName());
        body.put("primaryKeyType", getType(service.getPrimaryKeyColumnName(), service.getPrimaryKeyColumnClass()));
        final TreeMap<String, String> columns = new TreeMap<String, String>();
        body.put("columns", columns);
        
        Class value;
        for (Map.Entry<String, Class> entry : service.getTypeMap().entrySet()) {
            columns.put(entry.getKey(), getType(entry.getKey(), entry.getValue()));
        }
        
        setResponseBody(request, 200, body);
    }
    
//    @Override
//    public Node getServingNode(HttpServletRequest request, LinkedList<String> pathList, final int pathIndex) {
//        return isServingNode(request, pathList, pathIndex, supportedMethods(), getErrorBaseCode()) ? this : null;
//    }
//    
    public static boolean isServingNode(HttpServletRequest request, 
            LinkedList<String> pathList, final int pathIndex,
            final Set<String> supportedMethods, int errorBaseCode) {
        final String method = request.getMethod();
        LOG.trace("   mapping {} {} for {} ({})", new Object[] {
            method, request.getRequestURI(), pathIndex, pathList.size()
        });
        
            
        // support '' and /
        if (pathIndex == pathList.size() || 
                (pathIndex == pathList.size()-1 && pathList.getLast().isEmpty())) {
            if (supportedMethods.contains(method)) {
                return true;
            }
            else {
                throw new MethodNotAllowedException(errorBaseCode + ERR_OFFSET_METHOD, 
                        method, null, supportedMethods);
            }
        }

        // support /{id} and /{id}/ 
        if ((pathIndex == pathList.size()-1) ||
                (pathIndex == pathList.size()-2 && pathList.getLast().isEmpty())) {

            request.setAttribute(REQUEST_ATTR_FILENAME, pathList.get(pathIndex));
            if (supportedMethods.contains(method)) {
                return true;
            }
            else {
                throw new MethodNotAllowedException(errorBaseCode + ERR_OFFSET_METHOD, 
                        method, null, supportedMethods);
            }
        }
        
        return false;
    }
    
    
    public static String getType(String key, Class value) {
        if (Long.class.equals(value) ||
                Integer.class.equals(value) ||
                Short.class.equals(value) ||
                Byte.class.equals(value)) {
            return "number";
        }
        else if (String.class.equals(value)) {
            return "email".equals(key) ? "email" : "text";
        }
        else if (Boolean.class.equals(value)) {
            return "boolean";
        }
        else if (Date.class.equals(value)) {
            return "datetime";
        }
        return value.getSimpleName();
    }
    
    /**
     * Override to implement a read-only Leaf.
     * @return the supported HTTP methods for this CrudLeaf
     */
    protected Set<String> supportedMethods() {
        return SUPPORTED_METHODS;
    }

    @Override
    protected void updateResource(HttpServletRequest request, HttpServletResponse response, JKey jKey, J body) throws ServletException, IOException {
        final T domain = converter.convertJson(body);
        
        if (null != domain && null != jKey.getId()) {
            service.update(domain);
            if ("302".equals(request.getParameter(REQUEST_PARAM_EXPECTS))) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
            else {
                final J responseBody = converter.convertDomain(domain);
                setResponseBody(request, STATUS_OK, responseBody);
            }
        }
    }
    
    protected J convertWithInner(HttpServletRequest request, HttpServletResponse response,
            String domain, T from) {
        final J to = converter.convertDomain(from);
        addInnerObjects(request, response, domain, to);
        return to;
    }

    public BaseConverter<J, T> getConverter() {
        return converter;
    }

    public void setConverter(BaseConverter<J, T> converter) {
        this.converter = converter;
    }

    public S getService() {
        return service;
    }
    
    public void setService(S service) {
        this.service = service;
    }
}
