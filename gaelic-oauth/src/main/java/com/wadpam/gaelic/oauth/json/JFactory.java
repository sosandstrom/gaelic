package com.wadpam.gaelic.oauth.json;

import com.wadpam.gaelic.json.JBaseObject;

/**
 * A ConnectionFactory entity.
 * the ID is mapped to providerId.
 * @author sosandstrom
 */
public class JFactory extends JBaseObject {

    private String clientId;

    private String clientSecret;
    
    /** Base URL to the signin provider */
    private String baseUrl;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
