/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.net;

import com.wadpam.gaelic.GaelicServlet;
import com.wadpam.gaelic.Node;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
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
    public static final String MIME_FORM = "application/x-www-form-urlencoded";
    
    public static final String SEPARATOR_QUERY = "?";
    public static final String SEPARATOR_FRAGMENT = "#";
    
    static final List<String> CONTENT_METHODS_LIST = Arrays.asList(Node.METHOD_POST, Node.METHOD_PUT);
    static final Set<String> CONTENT_METHODS = new HashSet<String>(CONTENT_METHODS_LIST);
    
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
    
    public static Map<String, String> asMap(String... nameValues) {
        final TreeMap<String, String> map = new TreeMap<String, String>();
        
        if (null != nameValues) {
            for (int i = 0; i+1 < nameValues.length; i += 2) {
                map.put(nameValues[i], nameValues[i+1]);
            }
        }
        
        return map;
    }
    
    public <J> J  delete(String url, Class<J> responseClass) {
        return exchange(Node.METHOD_DELETE, url, null, null, responseClass);
    }
    
    public <J> J exchange(String method, String url, 
            Map<String, String> requestHeaders,
            Object requestBody, Class<J> responseClass) {
        final NetworkResponse<J> response = exchangeForResponse(method, url, 
                requestHeaders, requestBody, responseClass, true);
        return response.getBody();
    }
    
    public <J> NetworkResponse<J> exchangeForResponse(String method, String url, 
            Map<String, String> requestHeaders,
            Object requestBody, Class<J> responseClass, boolean followRedirects) {
        
        try {
            
            // expand url?
            if (null != requestBody && !CONTENT_METHODS.contains(method)) {
                Map<String, Object> paramMap = requestBody instanceof Map ? (Map) requestBody : 
                        GaelicServlet.MAPPER.convertValue(requestBody, Map.class);
                url = expandUrl(url, paramMap);
            }

            // create the connection
            URL u = new URL(url);
            HttpURLConnection con = (HttpURLConnection) u.openConnection();
            con.setInstanceFollowRedirects(followRedirects);

            // override default method
            if (null != method) {
                con.setRequestMethod(method);
            }
            LOG.info("{} {}", method, url);

            // Accept
            if (null != accept) {
                con.addRequestProperty(ACCEPT, accept);
                LOG.trace("{}: {}", ACCEPT, accept);
            }
            
            // Authorization
            if (null != authorization) {
                con.addRequestProperty(AUTHORIZATION, authorization);
                LOG.trace("{}: {}", AUTHORIZATION, authorization);
            }
            
            // other request headers:
            String contentType = null;
            if (null != requestHeaders) {
                for (Entry<String, String> entry : requestHeaders.entrySet()) {
                    con.addRequestProperty(entry.getKey(), entry.getValue());
                    LOG.info("{}: {}", entry.getKey(), entry.getValue());
                    if (CONTENT_TYPE.equalsIgnoreCase(entry.getKey())) {
                        contentType = entry.getValue();
                    }
                }
            }

            if (null != requestBody) {
                if (CONTENT_METHODS.contains(method)) {
                    
                    // content-type not specified in request headers?
                    if (null == contentType) {
                        contentType = MIME_JSON;
                        con.addRequestProperty(CONTENT_TYPE, MIME_JSON);
                    }
                    con.setDoOutput(true);
                    OutputStream out = con.getOutputStream();
                    if (MIME_JSON.equals(contentType)) {
                        GaelicServlet.MAPPER.writeValue(out, requestBody);
                    }
                    else {
                        // application/www-form-urlencoded
                        Map<String, Object> params = GaelicServlet.MAPPER.convertValue(requestBody, Map.class);
                        PrintWriter writer = new PrintWriter(out);
                        String content = expandUrl("", params);
                        writer.print(content.substring(1));
                        writer.flush();
                        LOG.info("Content: {}", content.substring(1));
                    }
                    out.close();
                }
            }

            NetworkResponse<J> response = new NetworkResponse<J>(
                    con.getResponseCode(),
                    con.getHeaderFields(),
                    con.getResponseMessage());
            LOG.info("HTTP {} {}", response.getCode(), response.getMessage());
            
            // response content to read and parse?
            if (null != con.getContentType()) {
                LOG.info("Content-Type: {}", con.getContentType());
                final InputStream in = con.getInputStream();
                
                if (con.getContentType().startsWith(MIME_JSON)) {
                    response.setBody(GaelicServlet.MAPPER.readValue(in, responseClass));
                    LOG.debug("Response JSON: {}", response.getBody());
                }
                
                in.close();
            }

            return response;
        }
        catch (IOException ioe) {
            throw new RuntimeException(String.format("NetworkTemplate.exchange: %s", ioe.getMessage()), ioe);
        }
    }
    
    public static String expandUrl(final String url, Map<String, Object> paramMap) {
        return expandUrl(url, SEPARATOR_QUERY, paramMap);
    }
    
    public static String expandUrl(final String url, final String separator, Map<String, Object> paramMap) {
        StringBuffer sb = new StringBuffer(url);

        for (Entry<String, Object> entry : paramMap.entrySet()) {
            sb.append(-1 < sb.indexOf(separator) ? "&" : separator);
            sb.append(entry.getKey());
            sb.append('=');
            try {
                sb.append(URLEncoder.encode(entry.getValue().toString(), "UTF-8"));
            }
            catch (UnsupportedEncodingException shouldNeverHappen) {
                LOG.error("Encoding parameter", shouldNeverHappen);
            }
        }

        return sb.toString();
    }
    
    public <J> J  get(String url, Class<J> responseClass, Object... requestBody) {
        return get(url, null, responseClass, requestBody);
    }
    
    public <J> J  get(String url, Map<String, String> requestHeaders, 
            Class<J> responseClass, Object... requestBody) {
        final Object requestParams = null != requestBody && 1 == requestBody.length ? requestBody[0] : null;
        return exchange(Node.METHOD_GET, url, requestHeaders, requestParams, responseClass);
    }
    
    public String getForLocation(String url, Object... requestBody) {
        return getForLocationWithHeaders(url, null, requestBody);
    }
    
    public String getForLocationWithHeaders(String url, Map<String, String> requestHeaders, 
            Object... requestBody) {
        final Object requestParams = null != requestBody && 1 == requestBody.length ? requestBody[0] : null;
        NetworkResponse<Void> response = exchangeForResponse(Node.METHOD_GET, url, 
                requestHeaders, requestParams, Void.class, false);
        return response.getHeader(LOCATION);
    }
    
    public <J> J  post(String url, Object requestBody, Class<J> responseClass) {
        return post(url, null, requestBody, responseClass);
    }
    
    public <J> J  post(String url, Map<String, String> requestHeaders,
            Object requestBody, Class<J> responseClass) {
        return exchange(Node.METHOD_POST, url, requestHeaders, requestBody, responseClass);
    }

    public String postForLocation(String url, Object requestBody) {
        return postForLocationWithHeaders(url, null, requestBody);
    }
    
    public String postForLocationWithHeaders(String url, Map<String, String> requestHeaders, 
            Object requestBody) {
        NetworkResponse<Void> response = exchangeForResponse(Node.METHOD_POST, url, 
                requestHeaders, requestBody, Void.class, false);
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
