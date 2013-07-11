/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.crud;

import com.wadpam.gaelic.tree.LeafAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author sosandstrom
 */
public class BasicCrudObservable<J extends Serializable, T> implements CrudObservable {

    protected final ArrayList<CrudListener> listeners = new ArrayList<CrudListener>();
    private CrudService<T> service;
    private final LeafAdapter<J> leaf;

    public BasicCrudObservable(LeafAdapter<J> leaf, CrudService<T> service) {
        this.service = service;
        this.leaf = leaf;
    }

    @Override
    public void addListener(CrudListener listener) {
        listeners.add(listener);
    }
    
    @Override
    public void removeListener(CrudListener listener) {
        listeners.remove(listener);
    }
    
    public void preService(HttpServletRequest request, String namespace,
            int operation, Object json, Object domain, Serializable id) {
        for (CrudListener l : listeners) {
            l.preService(leaf, service, request, namespace, 
                    operation, json, domain, id);
        }
    }
    
    public void postService(HttpServletRequest request, String namespace,
            int operation, Object json, Serializable id, Object serviceResponse) {
        for (CrudListener l : listeners) {
            l.postService(leaf, service, request, namespace, 
                    operation, json, id, serviceResponse);
        }
    }

    public void setService(CrudService<T> service) {
        this.service = service;
    }
}
