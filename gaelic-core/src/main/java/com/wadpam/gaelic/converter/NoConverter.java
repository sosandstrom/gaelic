/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.converter;

import java.io.Serializable;

/**
 *
 * @author sosandstrom
 */
public class NoConverter extends BaseConverter {

    public NoConverter(Class commonClass) {
        super(commonClass, commonClass);
    }

    @Override
    public Serializable convertDomain(Object from) {
        return (Serializable) from;
    }

    @Override
    public Object convertJson(Serializable from) {
        return from;
    }
    
    @Override
    public void convertDomain(Object from, Serializable to) {
        throw new UnsupportedOperationException("No conversion should occur."); 
    }

    @Override
    public void convertJson(Serializable from, Object to) {
        throw new UnsupportedOperationException("No conversion should occur."); 
    }

}
