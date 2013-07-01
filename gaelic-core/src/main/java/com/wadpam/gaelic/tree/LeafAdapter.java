/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wadpam.gaelic.tree;

import com.wadpam.gaelic.GaelicServlet;
import com.wadpam.gaelic.json.JKey;
import com.wadpam.gaelic.Node;
import com.wadpam.gaelic.exception.BadRequestException;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
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
    public static final int ERR_PAGESIZE = ERR_BASE + 10;
    
    /** Contains DELETE, GET and POST */
    protected static final HashSet<String> SUPPORTED_METHODS = new HashSet();
    
    static {
        SUPPORTED_METHODS.add(METHOD_DELETE);
        SUPPORTED_METHODS.add(METHOD_GET);
        SUPPORTED_METHODS.add(METHOD_POST);
    }
    
    protected final Class jsonClass;

    public LeafAdapter(Class jsonClass) {
        this.jsonClass = jsonClass;
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
    protected void createResource(HttpServletRequest request, HttpServletResponse response, JKey jKey, J body) {
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final JKey jKey = (JKey) request.getAttribute(REQUEST_ATTR_JKEY);
        
        if (null != jKey.getId()) {
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
        
        JKey parentKey = null, key = null;
        for (int i = pathIndex-1; i < pathList.size(); i += 2) {
            final String kind = pathList.get(i);
            final String id = i+1 < pathList.size() ? pathList.get(i+1) : null;
            key = new JKey();
            key.setParentKey(parentKey);
            key.setKind(kind);
            key.setId(id);

            parentKey = key;
        }
        
        request.setAttribute(REQUEST_ATTR_JKEY, key);
        
        final String method = request.getMethod();
        return isSupported(method) ? this : null;
    }

    /**
     * Invoked for GET ../{id}.
     * This implementation does nothing. Override to implement.
     * @param request
     * @param response
     * @param jKey JKey with non-null id
     */
    protected void getResourceByKey(HttpServletRequest request, HttpServletResponse response, JKey jKey) {
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
            JKey jKey, int pageSize, String cursorKey) {
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
    protected void updateResource(HttpServletRequest request, HttpServletResponse response, JKey jKey, J body) {
    }

}
