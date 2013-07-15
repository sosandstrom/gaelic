/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.social;

import java.io.IOException;

/**
 *
 * @author sosandstrom
 */
public class ITestTemplate extends SocialTemplate {

    public ITestTemplate(String access_token) {
        super(access_token);
    }

    @Override
    public SocialProfile getProfile() throws IOException {
        SocialProfile p = new SocialProfile();
        p.setId(access_token);
        p.setEmail(String.format("%s@example.com", access_token));
        p.setUsername(access_token);
        p.setDisplayName(access_token);
        return p;
    }
    
}
