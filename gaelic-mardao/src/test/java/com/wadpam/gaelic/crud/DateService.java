/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.crud;

import com.wadpam.gaelic.dao.DDateDaoBean;
import com.wadpam.gaelic.domain.DDate;
import net.sf.mardao.core.dao.Dao;

/**
 *
 * @author sosandstrom
 */
public class DateService extends MardaoCrudService<DDate, Long, Dao<DDate, Long>>{

    public DateService() {
        super(DDate.class, Long.class, DDateDaoBean.class);
    }
    
}
