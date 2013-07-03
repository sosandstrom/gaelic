/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wadpam.gaelic.tree;

import com.wadpam.gaelic.GaelicServlet;
import com.wadpam.gaelic.json.JKey;
import com.wadpam.gaelic.Node;
import com.wadpam.gaelic.exception.BadRequestException;
import com.wadpam.gaelic.exception.MethodNotAllowedException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.TreeSet;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sosandstrom
 */
public class LeafAdapter<J extends Object> extends Node {
    
    public static final String REQUEST_PARAM_PAGESIZE = "pageSize";
    public static final String REQUEST_PARAM_CURSORKEY = "cursorKey";
    public static final String REQUEST_ATTR_JKEY = "com.wadpam.gaelic.jKey";
    
    public static final int ERR_BASE = GaelicServlet.ERROR_CODE_LEAF_BASE;
    public static final int ERR_METHOD = ERR_BASE + 5;
    public static final int ERR_PAGESIZE = ERR_BASE + 10;
    
    /** Contains DELETE, GET and POST */
    protected static final TreeSet<String> SUPPORTED_METHODS = new TreeSet();
    
    static {
        SUPPORTED_METHODS.add(METHOD_DELETE);
        SUPPORTED_METHODS.add(METHOD_GET);
        SUPPORTED_METHODS.add(METHOD_POST);
    }
    
    protected final Class jsonClass;
    protected final String kind;

    public LeafAdapter(Class jsonClass, String kind) {
        this.jsonClass = jsonClass;
        this.kind = kind;
    }

    public LeafAdapter(Class jsonClass) {
        this(jsonClass, null);
    }

    /**
     * Invoked for POST ../ without id part of key.
     * This implementation does nothing. Override to implement.
     * 
     * @param request
     * @param response
     * @param jKey JKey with null id
     * @param body as parsed by {@link #getRequestBody(javax.servlet.http.HttpServletRequest)}
     */
    protected void createResource(HttpServletRequest request, HttpServletResponse response, JKey jKey, J body) throws ServletException, IOException {
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final JKey jKey = (JKey) request.getAttribute(REQUEST_ATTR_JKEY);
        
        if (null != jKey.getId()) {

            // me alias
            if ("me".equals(jKey.getId())) {
                jKey.setId(getCurrentUsername());
            }

            getResourceByKey(request, response, jKey);
        }
        else {
            final String pageSizeValue = request.getParameter(REQUEST_PARAM_PAGESIZE);
            int pageSize = 10;
            if (null != pageSizeValue) {
                try {
                    pageSize = Integer.parseInt(pageSizeValue);
                }
                catch (NumberFormatException bad) {
                    throw new BadRequestException(ERR_PAGESIZE, String.format("pageSize must be int: %s", bad.getMessage()), null);
                }
            }
            final String cursorKey = request.getParameter(REQUEST_PARAM_CURSORKEY);
            getResourcesPage(request, response, jKey, pageSize, cursorKey);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final JKey jKey = (JKey) request.getAttribute(REQUEST_ATTR_JKEY);
        final J body = getRequestBody(request);
        
        if (null != jKey.getId()) {
            updateResource(request, response, jKey, body);
        }
        else {
            createResource(request, response, jKey, body);
        }
    }
    
    protected J getRequestBody(HttpServletRequest request) throws IOException {
        J body = null;
        
        if (request.getContentType().startsWith(GaelicServlet.MEDIA_TYPE_JSON)) {
            ServletInputStream in = request.getInputStream();
            body = (J) GaelicServlet.MAPPER.readValue(in, jsonClass);
        }
        LOG.debug("Parsed request Content-Type: {} into {}", request.getContentType(), body);
        
        return body;
    }
    
    @Override
    public Node getServingNode(HttpServletRequest request, LinkedList<String> pathList, int pathIndex) {
        LOG.debug("path[{}]={}", pathIndex, pathList.get(pathIndex-1));
        int i = pathIndex-1;
        
        // Entity key first:
        final JKey key = new JKey();
        key.setKind(null != this.kind ? this.kind : pathList.get(i));
        
        // resource/{id} or just resource/ ?
        if (1 == (pathList.size() - pathIndex) %2) {
            key.setId(pathList.get(i+1));
            i+=2;
        }
        else {
            i++;
        }
        
        // then parent keys
        JKey childKey = key, parentKey;
        for ( ; i < pathList.size(); i += 2) {
            parentKey = new JKey();
            parentKey.setKind(pathList.get(i));
            parentKey.setId(pathList.get(i+1));
            
            childKey.setParentKey(parentKey);
            childKey = parentKey;
        }
        
        request.setAttribute(REQUEST_ATTR_JKEY, key);
        request.setAttribute(CrudLeaf.REQUEST_ATTR_FILENAME, key.getId());
        
        final String method = request.getMethod();
        if (!isSupported(method)) {
            throw new MethodNotAllowedException(ERR_METHOD, 
                method, null, SUPPORTED_METHODS);

        }
        return this;
    }

    /**
     * Invoked for GET ../{id}.
     * This implementation does nothing. Override to implement.
     * @param request
     * @param response
     * @param jKey JKey with non-null id
     */
    protected void getResourceByKey(HttpServletRequest request, HttpServletResponse response, JKey jKey) throws ServletException, IOException {
    }

    /**
     * Invoked for GET ../ without id part of key.
     * This implementation does nothing. Override to implement.
     * @param request
     * @param response
     * @param jKey JKey with null id
     * @param pageSize size of requested page
     * @param cursorKey null for first page
     */
    protected void getResourcesPage(HttpServletRequest request, HttpServletResponse response, 
            JKey jKey, int pageSize, String cursorKey) throws ServletException, IOException {
    }

    public boolean isSupported(final String method) {
        return SUPPORTED_METHODS.contains(method);
    }

    /**
     * Invoked for POST ../{id}.
     * This implementation does nothing. Override to implement.
     * 
     * @param request
     * @param response
     * @param jKey JKey with non-null id
     * @param body as parsed by {@link #getRequestBody(javax.servlet.http.HttpServletRequest)}
     */
    protected void updateResource(HttpServletRequest request, HttpServletResponse response, JKey jKey, J body) throws ServletException, IOException {
    }

}
