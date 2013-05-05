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
    public DDate get(String parentKeyString, Long id) {
        if (null == id) {
            return null;
        }
        DDate bean = new DDate(id, new Date());
        return bean;
    }

}
