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
public class ConnectionServiceImpl extends MardaoCrudService<DConnection, Long, DConnectionDao> 
    implements ConnectionService {

    public ConnectionServiceImpl() {
        super(DConnection.class, Long.class, DConnectionDaoBean.class);
    }

    @Override
    public DConnection findByAccessToken(String accessToken) {
        return dao.findByAccessToken(accessToken);
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
                to.append(DConnection.ROLE_SEPARATOR);
            }
            to.append(s.trim());
            first = false;
        }
        return to.toString();
    }

}
