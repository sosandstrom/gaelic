/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.oauth.provider.domain;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import net.sf.mardao.core.Parent;
import net.sf.mardao.core.domain.AbstractLongEntity;

/**
 *
 * @author sosandstrom
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"accessToken"}),
    @UniqueConstraint(columnNames = {"refreshToken"})})
public class Do2pToken extends AbstractLongEntity {
    
    @Parent(kind="Do2pProfile")
    private Object profileKey;

    @Basic
    private String accessToken;
    
    @Basic
    private String refreshToken;
    
    @Basic
    private Date expiryDate;

    @Basic
    private String scope;
    
    @Basic
    private Long clientId;

    public Object getProfileKey() {
        return profileKey;
    }

    public void setProfileKey(Object profileKey) {
        this.profileKey = profileKey;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

}
