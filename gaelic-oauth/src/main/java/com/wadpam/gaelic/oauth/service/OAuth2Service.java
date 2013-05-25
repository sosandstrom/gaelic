/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.oauth.service;

import com.wadpam.gaelic.crud.CrudObservable;
import com.wadpam.gaelic.json.RestResponse;
import com.wadpam.gaelic.oauth.domain.DConnection;

/**
 *
 * @author sosandstrom
 */
public interface OAuth2Service extends CrudObservable {
    
    public static final String PROVIDER_ID_ITEST = "itest";
    public static final String PROVIDER_ID_FACEBOOK = "facebook";
    public static final String PROVIDER_ID_GOOGLE = "google";
    public static final String PROVIDER_ID_SALESFORCE = "salesforce";
    public static final String PROVIDER_ID_TWITTER = "twitter";
    
    public static final int OPERATION_REGISTER_FEDERATED = 1001;
    
    /**
     * 
     * @param access_token
     * @param providerId
     * @param providerUserId
     * @param secret
     * @param expires_in
     * @return the userId associated with the Connection, null if new Connection
     */
    public RestResponse<DConnection> registerFederated(
            String access_token, 
            String providerId,
            String providerUserId,
            String secret,
            Integer expiresInSeconds,
            String appArg0,
            String domain);    
    
    Long getUserId(Object userKey);
    
    void setCustomProvider(ProviderFactory customProvider);
}
