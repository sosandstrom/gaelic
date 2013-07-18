/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.oauth.provider.service;

import com.wadpam.gaelic.crud.MardaoCrudService;
import com.wadpam.gaelic.oauth.provider.dao.Do2pClientDao;
import com.wadpam.gaelic.oauth.provider.dao.Do2pClientDaoBean;
import com.wadpam.gaelic.oauth.provider.domain.Do2pClient;
import java.util.UUID;

/**
 *
 * @author sosandstrom
 */
public class ClientService extends MardaoCrudService<Do2pClient, Long, Do2pClientDao> {

    public ClientService() {
        super(Do2pClient.class, Long.class, Do2pClientDaoBean.class);
    }

    @Override
    public String create(Do2pClient domain) {
        UUID secret = UUID.randomUUID();
        domain.setSecret(secret.toString());
        return super.create(domain);
    }

}
