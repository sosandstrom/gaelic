/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.converter;

import com.wadpam.gaelic.json.JBaseObject;
import net.sf.mardao.core.domain.AbstractLongEntity;

/**
 *
 * @author sosandstrom
 */
public class LongConverter<J extends JBaseObject, T extends AbstractLongEntity> 
        extends MardaoConverter<J, T> {

    public LongConverter(Class<J> jsonClass, Class<T> domainClass) {
        super(jsonClass, domainClass);
    }

    @Override
    public void convertDomain(T from, J to) {
        convertLongEntity(from, to);
    }
    
    @Override
    public void convertJson(J from, T to) {
        convertJLong(from, to);
    }

}
