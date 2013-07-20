/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.social;

import com.wadpam.gaelic.exception.ForbiddenException;
import com.wadpam.gaelic.net.NetworkTemplate;
import java.io.IOException;
import java.util.Map;


/**
 *
 * @author sosandstrom
 */
public class SocialTemplate extends NetworkTemplate {

    protected final String access_token;

    public SocialTemplate(String access_token) {
        this(access_token, "https://graph.facebook.com");
    }

    public SocialTemplate(String access_token, String baseUrl) {
        super(baseUrl);
        this.access_token = access_token;
    }
    
    public static SocialTemplate create(String providerId, String access_token, 
            String baseUrl, String domain) {
        if ("facebook".equals(providerId)) {
            return new SocialTemplate(access_token);
        }
        if ("itest".equals(providerId) && "itest".equals(domain)) {
            return new ITestTemplate(access_token);
        }
        throw new IllegalArgumentException(String.format("No such provider %s.", providerId));
    }
    
    public SocialProfile getProfile() throws IOException {
        Map<String, Object> props = get(String.format("%s/me", getBaseUrl()), Map.class);
        return parseProfile(props);
    }

    @Override
    public <J> J exchange(String method, String url, 
            Map<String,String> requestHeaders, 
            Object requestBody, Class<J> responseClass) {
        
        // OAuth access_token
        if (null != access_token) {
            url = String.format("%s%saccess_token=%s",
                    url, url.contains("?") ? "&" : "?", access_token);
        }
        
        return super.exchange(method, url, 
                requestHeaders, requestBody, responseClass);
    }
    
    /**
     * Property names for Facebook - Override to customize
     * @param props
     * @return 
     */
    protected SocialProfile parseProfile(Map<String, Object> props) {
        if (null == props.get("id")) {
            Map<String, Object> error = (Map<String, Object>) props.get("error");
            Integer code = (Integer) error.get("code");
            throw new ForbiddenException(code, (String) error.get("message"), null);
        }
        return SocialProfile.with(props)
                .displayName("name")
                .first("first_name")
                .last("last_name")
                .id("id")
                .username("username")
                .profileUrl("link")
                .build();
    }
}
