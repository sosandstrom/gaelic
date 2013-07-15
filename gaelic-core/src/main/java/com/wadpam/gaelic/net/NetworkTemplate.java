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
    public static final String ACCEPT = "Accept";
    public static final String AUTHORIZATION = "Authorization";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String LOCATION = "Location";
    
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
        final NetworkResponse<J> response = exchangeForResponse(method, url, requestBody, responseClass);
        return response.getBody();
    }
    
    public <J> NetworkResponse<J> exchangeForResponse(String method, String url, Object requestBody, Class<J> responseClass) {
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
                con.addRequestProperty(ACCEPT, accept);
            }
            
            // Authorization
            if (null != authorization) {
                con.addRequestProperty(AUTHORIZATION, authorization);
            }

            if (null != requestBody) {
                con.addRequestProperty(CONTENT_TYPE, MIME_JSON);
                con.setDoOutput(true);
                OutputStream out = con.getOutputStream();
                GaelicServlet.MAPPER.writeValue(out, requestBody);
                out.close();
            }

            NetworkResponse<J> response = new NetworkResponse<J>(
                    con.getResponseCode(),
                    con.getHeaderFields(),
                    con.getResponseMessage());
            InputStream in = con.getInputStream();

            if (null == con.getContentType() || con.getContentType().startsWith(MIME_JSON)) {
                response.setBody(GaelicServlet.MAPPER.readValue(in, responseClass));
                LOG.debug("Response JSON: {}", response.getBody());
            }

            return response;
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

    public String postForLocation(String url, Object requestBody) {
        NetworkResponse<Void> response = exchangeForResponse(Node.METHOD_POST, url, requestBody, Void.class);
        return response.getHeader(LOCATION);
    }
    
    // --- getters and setters ---
    
    public String getBaseUrl() {
        return baseUrl;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }
    
}
