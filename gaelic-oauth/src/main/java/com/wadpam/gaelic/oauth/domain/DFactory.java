/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wadpam.gaelic.oauth.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import net.sf.mardao.core.domain.AbstractStringEntity;

/**
 * A ConnectionFactory entity.
 * the ID is mapped to providerId.
 * @author sosandstrom
 */
@Entity
public class DFactory extends AbstractStringEntity {

    @Basic
    private String clientId;

    @Basic
    private String clientSecret;
    
    /** Base URL to the signin provider */
    @Basic
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
