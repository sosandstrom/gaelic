/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.crud;

import com.wadpam.gaelic.json.JCursorPage;
import com.wadpam.gaelic.tree.LeafAdapter;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author sosandstrom
 */
public class CrudListenerAdapter<J extends Serializable, T>
        implements CrudListener<J, T> {

    @Override
    public void preService(LeafAdapter<J> leaf, 
            CrudService<T> service, 
            HttpServletRequest request, 
            String namespace, 
            int operation, 
            Object json, 
            Object domain, 
            Serializable id) {
        switch (operation) {
            case CREATE:
                preCreate(leaf, service, request, namespace, json, domain);
                break;
            case GET:
                preGet(leaf, service, request, namespace, id);
                break;
            case UPDATE:
                preUpdate(leaf, service, request, namespace, json, domain, id);
                break;
            case DELETE:
                preDelete(leaf, service, request, namespace, id);
                break;
            case GET_PAGE:
                break;
            case WHAT_CHANGED:
                break;
            case UPSERT_BATCH:
                break;
            case DELETE_BATCH:
                break;
            case GET_EXISTING:
                break;
        }
    }

    @Override
    public void postService(LeafAdapter<J> leaf, 
            CrudService<T> service, 
            HttpServletRequest request, 
            String namespace, 
            int operation, 
            Object json, 
            Serializable id, 
            Object serviceResponse) {
        switch (operation) {
            case CREATE:
                postCreate(leaf, service, request, namespace, json, id, serviceResponse);
                break;
            case GET:
                postGet(leaf, service, request, namespace, id, serviceResponse, json);
                break;
            case UPDATE:
                postUpdate(leaf, service, request, namespace, json, id, serviceResponse);
                break;
            case DELETE:
                postDelete(leaf, service, request, namespace, id);
                break;
            case GET_PAGE:
                postGetPage(leaf, service, request, namespace, (JCursorPage<J>) json, (String) id, (JCursorPage<T>) serviceResponse);
                break;
            case WHAT_CHANGED:
                break;
            case UPSERT_BATCH:
                break;
            case DELETE_BATCH:
                break;
            case GET_EXISTING:
                break;
        }
    }

    /**
     * Override to implement the pre-service create callback
     * @param leaf
     * @param service
     * @param request
     * @param namespace
     * @param json
     * @param domain
     */
    protected void preCreate(LeafAdapter leaf, CrudService service, HttpServletRequest request, String namespace, Object json, Object domain) {
    }

    protected void preGet(LeafAdapter leaf, CrudService service, HttpServletRequest request, String namespace, Serializable id) {
    }

    protected void preUpdate(LeafAdapter leaf, CrudService service, HttpServletRequest request, String namespace, Object json, Object domain, Serializable id) {
    }

    protected void preDelete(LeafAdapter leaf, CrudService service, HttpServletRequest request, String namespace, Serializable id) {
    }
    
    /**
     * Override to implement the post-service create callback
     * @param leaf
     * @param service
     * @param request
     * @param domain
     * @param json
     * @param id
     * @param serviceResponse 
     */
    protected void postCreate(LeafAdapter leaf, CrudService service, HttpServletRequest request, String domain, Object json, Serializable id, Object serviceResponse) {
    }

    /**
     * 
     * @param leaf
     * @param service
     * @param request
     * @param namespace
     * @param id
     * @param serviceResponse
     * @param jsonResponse 
     */
    protected void postGet(LeafAdapter leaf, CrudService service, HttpServletRequest request, 
            String namespace, Serializable id, Object serviceResponse, Object jsonResponse) {
    }

    protected void postUpdate(LeafAdapter leaf, CrudService service, HttpServletRequest request, 
            String namespace, Object json, Serializable id, Object serviceResponse) {
    }

    protected void postDelete(LeafAdapter leaf, CrudService service, HttpServletRequest request, 
            String namespace, Serializable id) {
    }

    protected void postGetPage(LeafAdapter<J> leaf, CrudService<T> service, HttpServletRequest request, 
            String namespace, JCursorPage<J> jPage, String cursorKey, JCursorPage<T> serviceResponse) {
    }

}
