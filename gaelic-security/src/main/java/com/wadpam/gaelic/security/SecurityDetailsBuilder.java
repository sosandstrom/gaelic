/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.security;

import java.util.Collection;

/**
 *
 * @author sosandstrom
 */
public class SecurityDetailsBuilder {
    
    private final BasicSecurityDetails details;
    
    protected SecurityDetailsBuilder() {
        details = null;
    }

    private SecurityDetailsBuilder(String username) {
        details = new BasicSecurityDetails();
        details.setUsername(username);
    }
    
    public static SecurityDetailsBuilder with(String username) {
        return new SecurityDetailsBuilder(username);
    }
    
    public SecurityDetailsBuilder password(String password) {
        details.setPassword(password);
        return this;
    }
    
    public SecurityDetailsBuilder userKey(Object userKey) {
        details.setUserKey(userKey);
        return this;
    }
    
    public SecurityDetailsBuilder roles(Collection<String> roles) {
        details.setRoles(roles);
        return this;
    }
    
    public BasicSecurityDetails build() {
        return details;
    }
    
    public class BasicSecurityDetails implements SecurityDetails {
        private String username;
        private String password;
        private Object userKey;
        private Collection<String> roles;

        @Override
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        @Override
        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public Object getUserKey() {
            return userKey;
        }

        public void setUserKey(Object userId) {
            this.userKey = userId;
        }

        @Override
        public Collection<String> getRoles() {
            return roles;
        }

        public void setRoles(Collection<String> roles) {
            this.roles = roles;
        }
    }
}
