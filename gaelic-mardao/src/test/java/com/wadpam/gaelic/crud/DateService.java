/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.crud;

import com.wadpam.gaelic.dao.DDateDao;
import com.wadpam.gaelic.dao.DDateDaoBean;
import com.wadpam.gaelic.domain.DDate;

/**
 *
 * @author sosandstrom
 */
public class DateService extends MardaoCrudService<DDate, Long, DDateDao>{

    public DateService() {
        super(DDate.class, Long.class, DDateDaoBean.class);
    }
    
}
