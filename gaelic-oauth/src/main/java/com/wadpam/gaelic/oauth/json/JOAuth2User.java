/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.oauth.json;

import com.wadpam.gaelic.json.JBaseObject;
import java.util.Collection;

/**
 *
 * @author sosandstrom
 */
public class JOAuth2User extends JBaseObject {

    private String displayName;
    
    private String email;
    
    private String profileLink;
    
    private Collection<String> roles;

    private String thumbnailUrl;

    private String username;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfileLink() {
        return profileLink;
    }

    public void setProfileLink(String profileLink) {
        this.profileLink = profileLink;
    }

    public Collection<String> getRoles() {
        return roles;
    }

    public void setRoles(Collection<String> roles) {
        this.roles = roles;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
    
}
