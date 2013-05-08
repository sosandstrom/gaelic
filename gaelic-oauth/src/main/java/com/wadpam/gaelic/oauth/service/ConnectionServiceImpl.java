/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.oauth.service;

import com.wadpam.gaelic.crud.MardaoCrudService;
import com.wadpam.gaelic.oauth.dao.DConnectionDao;
import com.wadpam.gaelic.oauth.dao.DConnectionDaoBean;
import com.wadpam.gaelic.oauth.domain.DConnection;
import java.util.ArrayList;

/**
 *
 * @author sosandstrom
 */
public class ConnectionServiceImpl extends MardaoCrudService<DConnection, String, DConnectionDao> 
    implements ConnectionService {

    public ConnectionServiceImpl() {
        super(DConnection.class, String.class, DConnectionDaoBean.class);
    }

    @Override
    public Iterable<DConnection> queryByAppArg0(String appArg0) {
        return dao.queryByAppArg0(appArg0);
    }
    
    public Iterable<DConnection> queryByProviderIdProviderUserId(String providerId, String providerUserId) {
        return dao.queryByProviderIdProviderUserId(providerId, providerUserId);
    }
    
    public static String convertRoles(Iterable<String> from) {
        if (null == from) {
            return null;
        }
        final StringBuffer to = new StringBuffer();
        boolean first = true;
        for (String s : from) {
            if (!first) {
                to.append(ROLE_SEPARATOR);
            }
            to.append(s.trim());
            first = false;
        }
        return to.toString();
    }
    
    public static ArrayList<String> convertRoles(String from) {
        final ArrayList<String> to = new ArrayList<String>();
        if (null != from) {
            final String roles[] = from.split(ROLE_SEPARATOR);
            for (String r : roles) {
                to.add(r.trim());
            }
        }
        return to;
    }

    public void setDConnectionDao(DConnectionDao dConnectionDao) {
        this.dao = dConnectionDao;
    }

}
