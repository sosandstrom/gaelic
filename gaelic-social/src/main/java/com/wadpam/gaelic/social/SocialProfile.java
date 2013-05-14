/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.social;

import java.util.Map;

/**
 *
 * @author sosandstrom
 */
public class SocialProfile {
    private String displayName;
    private String firstName;
    private String lastName;
    private String email;
    private String id;
    private String username;
    private String profileUrl;
    
    private Map<String, Object> props = null;
    
    public SocialProfile() {
    }

    protected SocialProfile(Map<String, Object> props) {
        this.props = props;
    }

    public static SocialProfile with(Map<String, Object> props) {
        return new SocialProfile(props);
    }
    
    public SocialProfile build() {
        props = null;
        return this;
    }

    public SocialProfile displayName(String propertyName) {
        this.displayName = (String) props.get(propertyName);
        return this;
    }
    
    public SocialProfile first(String propertyName) {
        this.firstName = (String) props.get(propertyName);
        return this;
    }
    
    public SocialProfile last(String propertyName) {
        this.lastName = (String) props.get(propertyName);
        return this;
    }
    
    public SocialProfile id(String propertyName) {
        this.id = (String) props.get(propertyName);
        return this;
    }
    
    public SocialProfile email(String propertyName) {
        this.email = (String) props.get(propertyName);
        return this;
    }
    
    public SocialProfile username(String propertyName) {
        this.username = (String) props.get(propertyName);
        return this;
    }
    
    public SocialProfile profileUrl(String propertyName) {
        this.profileUrl = (String) props.get(propertyName);
        return this;
    }
    
    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setProps(Map<String, Object> props) {
        this.props = props;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }
}
