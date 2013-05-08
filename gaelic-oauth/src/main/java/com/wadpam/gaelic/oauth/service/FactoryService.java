/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.oauth.service;

import com.wadpam.gaelic.crud.MardaoCrudService;
import com.wadpam.gaelic.oauth.dao.DFactoryDao;
import com.wadpam.gaelic.oauth.dao.DFactoryDaoBean;
import com.wadpam.gaelic.oauth.domain.DFactory;

/**
 *
 * @author sosandstrom
 */
public class FactoryService extends MardaoCrudService<DFactory, String, DFactoryDao> {

    public FactoryService() {
        super(DFactory.class, String.class, DFactoryDaoBean.class);
    }
    
    public void setDFactoryDao(DFactoryDao dFactoryDao) {
        this.dao = dFactoryDao;
    }
}
