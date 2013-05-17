/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.service;

import com.wadpam.gaelic.crud.MardaoCrudService;
import com.wadpam.gaelic.dao.DAppDomainDao;
import com.wadpam.gaelic.dao.DAppDomainDaoBean;
import com.wadpam.gaelic.domain.DAppDomain;
import com.wadpam.gaelic.security.SecurityDetailsService;
import com.wadpam.gaelic.security.SecurityInterceptor;
import java.util.Collection;
import java.util.Collections;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sosandstrom
 */
public class AppDomainService extends MardaoCrudService<DAppDomain, String, DAppDomainDao> 
        implements SecurityDetailsService {

    public AppDomainService() {
        super(DAppDomain.class, String.class, DAppDomainDaoBean.class);
    }

    @Override
    public Object loadUserDetailsByUsername(HttpServletRequest request, HttpServletResponse response, String uri, String authValue, Object userKey) {
        return dao.findByUsername((String) userKey);
    }

    @Override
    public Collection<String> getRolesFromUserDetails(Object details) {
        return null != details ? SecurityInterceptor.ROLES_ANONYMOUS : Collections.EMPTY_LIST;
    }

}
