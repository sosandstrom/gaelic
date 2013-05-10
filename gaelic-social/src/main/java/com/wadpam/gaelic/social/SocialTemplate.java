/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.social;

import com.wadpam.gaelic.GaelicServlet;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.TreeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author sosandstrom
 */
public class SocialTemplate {
    public static final String MIME_JSON = "application/json";
    
    protected static final Logger LOG = LoggerFactory.getLogger(SocialTemplate.class);
    
    private final String access_token;
    protected String accept = MIME_JSON;
    private final String baseUrl;

    public SocialTemplate(String access_token) {
        this(access_token, "https://graph.facebook.com");
    }

    public SocialTemplate(String access_token, String baseUrl) {
        this.access_token = access_token;
        this.baseUrl = baseUrl;
    }
    
    public SocialProfile getProfile() throws IOException {
        Map<String, Object> props = get(String.format("%s/me", getBaseUrl()));
        return parseProfile(props);
    }

    public String getBaseUrl() {
        return baseUrl;
    }
    
    protected Map<String, Object> get(String url) throws IOException {
        return exchange("GET", url, null);
    }
    
    protected Map<String, Object> exchange(String method, String url, Object requestBody) throws IOException {
        // OAuth access_token
        if (null != access_token) {
            url = String.format("%s%saccess_token=%s",
                    url, url.contains("?") ? "&" : "?", access_token);
        }
        
        // create the connection
        URL u = new URL(url);
        HttpURLConnection con = (HttpURLConnection) u.openConnection();
        
        // override default method
        if (null != method) {
            con.setRequestMethod(method);
        }
        
//        // OAuth access_token
//        if (null != access_token) {
//            con.addRequestProperty("Authorizaton", String.format("OAuth %s", access_token));
//        }
        
        // Accept
        if (null != accept) {
            con.addRequestProperty("Accept", accept);
        }
        
        InputStream in = con.getInputStream();
        
        Map<String,Object> map = new TreeMap<String, Object>();
        if (null == con.getContentType() || con.getContentType().startsWith(MIME_JSON)) {
            map = GaelicServlet.MAPPER.readValue(in, Map.class);
            LOG.debug("Response JSON: {}", map);
        }
        
        return map;
    }
    
    /**
     * Property names for Facebook - Override to customize
     * @param props
     * @return 
     */
    protected SocialProfile parseProfile(Map<String, Object> props) {
        return SocialProfile.with(props)
                .displayName("name")
                .first("first_name")
                .last("last_name")
                .id("id")
                .username("username")
                .build();
    }
}
