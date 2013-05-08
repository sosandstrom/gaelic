/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.oauth.service;

import com.wadpam.gaelic.crud.CrudService;
import com.wadpam.gaelic.oauth.domain.DConnection;

/**
 *
 * @author sosandstrom
 */
public interface ConnectionService extends CrudService<DConnection, String> {
    final String ROLE_SEPARATOR = ",";
    
    Iterable<DConnection> queryByProviderIdProviderUserId(String providerId, String providerUserId);

    Iterable<DConnection> queryByAppArg0(String appArg0);
    
}
