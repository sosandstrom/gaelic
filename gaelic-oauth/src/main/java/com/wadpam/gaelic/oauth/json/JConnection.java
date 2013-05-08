package com.wadpam.gaelic.oauth.json;

import com.wadpam.gaelic.json.JBaseObject;

/**
 * access_token is primary key (id).
 * @author sosandstrom
 */
public class JConnection extends JBaseObject {
    
    /** The name, as entered in the social site */
    private String displayName;

    /** Timestamp when the access token will expire */
    private Long expireTime;
    
    /** Link to user's image at social site */
    private String imageUrl;
    
    /** Link to user's profile at social site */
    private String profileUrl;

    /** provider id, facebook, twitter, salesforce */
    private String providerId;

    /** The user's id at social site */
    private String providerUserId;
    
    /** Long-lived token used to refresh access_token */
    private String refreshToken;
    
    /** Used by twitter */
    private String secret;
    
    /** User's id in this system */
    private String userId;
    
    /** User's roles, comma-separated, populated by registerFederated() */
    private String userRoles;
    
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getProviderUserId() {
        return providerUserId;
    }

    public void setProviderUserId(String providerUserId) {
        this.providerUserId = providerUserId;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(String userRoles) {
        this.userRoles = userRoles;
    }

}
