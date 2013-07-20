/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.oauth.provider.service;

import com.wadpam.gaelic.crud.MardaoCrudService;
import com.wadpam.gaelic.oauth.provider.dao.Do2pClientDao;
import com.wadpam.gaelic.oauth.provider.dao.Do2pClientDaoBean;
import com.wadpam.gaelic.oauth.provider.domain.Do2pClient;
import com.wadpam.gaelic.security.SecurityDetails;
import com.wadpam.gaelic.security.SecurityDetailsService;
import java.util.Collections;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sosandstrom
 */
public class ClientService extends MardaoCrudService<Do2pClient, Long, Do2pClientDao> 
        implements SecurityDetailsService {
    
    public static final String ATTR_CLIENT = "com.wadpam.gaelic.oauth.provider.ClientService.Do2pClient";

    public ClientService() {
        super(Do2pClient.class, Long.class, Do2pClientDaoBean.class);
    }

    @Override
    public String create(Do2pClient domain) {
        UUID secret = UUID.randomUUID();
        domain.setSecret(secret.toString());
        return super.create(domain);
    }

    @Override
    public SecurityDetails loadUserDetailsByUsername(HttpServletRequest request, HttpServletResponse response, String uri, String authValue, Object userKey) {
        Do2pClient client = get(null, (String) userKey);
        if (null == client) {
            LOG.info("No such client {}", userKey);
            return null;
        }
        
        request.setAttribute(ATTR_CLIENT, client);
        return BUILDER.with((String) userKey)
                .password(client.getSecret())
                .roles(Collections.EMPTY_LIST)
                .userKey(dao.getPrimaryKey(client))
                .build();
    }

}
