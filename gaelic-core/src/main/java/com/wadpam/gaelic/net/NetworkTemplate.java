/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.net;

import com.wadpam.gaelic.GaelicServlet;
import com.wadpam.gaelic.Node;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author sosandstrom
 */
public class NetworkTemplate {
    public static final String MIME_JSON = "application/json";
    
    protected static final Logger LOG = LoggerFactory.getLogger(NetworkTemplate.class);
    
    protected String accept = MIME_JSON;
    private final String baseUrl;
    private String authorization = null;

    public NetworkTemplate(String baseUrl) {
        this.baseUrl = baseUrl;
    }
    
    public NetworkTemplate() {
        this.baseUrl = null;
    }
    
    public <J> J  delete(String url, Class<J> responseClass) {
        return exchange(Node.METHOD_DELETE, url, null, responseClass);
    }
    
    public <J> J exchange(String method, String url, Object requestBody, Class<J> responseClass) {
        try {
            // create the connection
            URL u = new URL(url);
            HttpURLConnection con = (HttpURLConnection) u.openConnection();

            // override default method
            if (null != method) {
                con.setRequestMethod(method);
            }

            // Accept
            if (null != accept) {
                con.addRequestProperty("Accept", accept);
            }
            
            // Authorization
            if (null != authorization) {
                con.addRequestProperty("Authorization", authorization);
            }

            if (null != requestBody) {
                con.addRequestProperty("Content-Type", MIME_JSON);
                con.setDoOutput(true);
                OutputStream out = con.getOutputStream();
                GaelicServlet.MAPPER.writeValue(out, requestBody);
                out.close();
            }

            InputStream in = con.getInputStream();

            J responseBody = null;
            if (null == con.getContentType() || con.getContentType().startsWith(MIME_JSON)) {
                responseBody = GaelicServlet.MAPPER.readValue(in, responseClass);
                LOG.debug("Response JSON: {}", responseBody);
            }

            return responseBody;
        }
        catch (IOException ioe) {
            throw new RuntimeException("NetworkTemplate.exchange", ioe);
        }
    }
    
    public <J> J  get(String url, Class<J> responseClass) {
        return exchange(Node.METHOD_GET, url, null, responseClass);
    }
    
    public <J> J  post(String url, Object requestBody, Class<J> responseClass) {
        return exchange(Node.METHOD_POST, url, requestBody, responseClass);
    }

    // --- getters and setters ---
    
    public String getBaseUrl() {
        return baseUrl;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }
    
}
