/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.crud;

import com.wadpam.gaelic.domain.DDate;
import java.util.Date;
import java.util.TreeMap;

/**
 *
 * @author sosandstrom
 */
public class DateService extends CrudServiceAdapter<DDate, Long> {
    
    private final TreeMap<Long, DDate> DATES = new TreeMap<Long, DDate>();

    @Override
    public Long create(DDate domain) {
        final long millis = null != domain.getStartDate() ?
                domain.getStartDate().getTime() : System.currentTimeMillis();
        domain.setId(millis);
        DATES.put(millis, domain);
        return millis;
    }
    
    @Override
    public DDate get(String parentKeyString, Long id) {
        if (null == id) {
            return null;
        }
        
        if (DATES.containsKey(id)) {
            return DATES.get(id);
        }
        
        DDate bean = new DDate(id, new Date());
        return bean;
    }

    @Override
    public Long update(DDate domain) {
        DATES.put(domain.getId(), domain);
        return domain.getId();
    }

}
