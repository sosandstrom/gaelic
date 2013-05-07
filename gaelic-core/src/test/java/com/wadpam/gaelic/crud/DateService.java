/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.crud;

import com.wadpam.gaelic.domain.DDate;
import java.util.Date;

/**
 *
 * @author sosandstrom
 */
public class DateService extends CrudServiceAdapter<DDate, Long> {

    @Override
    public Long create(DDate domain) {
        final long millis = null != domain.getStartDate() ?
                domain.getStartDate().getTime() : System.currentTimeMillis();
        domain.setId(millis);
        return millis;
    }
    
    @Override
    public DDate get(String parentKeyString, Long id) {
        if (null == id) {
            return null;
        }
        DDate bean = new DDate(id, new Date());
        return bean;
    }

}
