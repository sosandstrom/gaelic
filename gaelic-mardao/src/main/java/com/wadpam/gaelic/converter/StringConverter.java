/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.converter;

import com.wadpam.gaelic.json.JBaseObject;
import net.sf.mardao.core.domain.AbstractStringEntity;

/**
 *
 * @author sosandstrom
 */
public class StringConverter<J extends JBaseObject, T extends AbstractStringEntity> 
        extends MardaoConverter<J, T> {

    public StringConverter(Class<J> jsonClass, Class<T> domainClass) {
        super(jsonClass, domainClass);
    }

    @Override
    public void convertDomain(T from, J to) {
        convertStringEntity(from, to);
    }
    
    @Override
    public void convertJson(J from, T to) {
        convertJString(from, to);
    }

}
