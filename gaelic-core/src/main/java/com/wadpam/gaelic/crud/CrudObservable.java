/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.crud;

/**
 *
 * @author sosandstrom
 */
public interface CrudObservable {

    void addListener(CrudListener listener);
    
    void removeListener(CrudListener listener);
    
}
