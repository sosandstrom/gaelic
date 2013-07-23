/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic;

import com.wadpam.gaelic.exception.MethodNotAllowedException;
import com.wadpam.gaelic.exception.RestException;
import com.wadpam.gaelic.json.JException;
import com.wadpam.gaelic.json.JKeyFactory;
import com.wadpam.gaelic.tree.Interceptor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sosandstrom
 */
public class GaelicServlet extends HttpServlet {
    
    public static final String MEDIA_TYPE_JSON = "application/json";
    public static final String MEDIA_TYPE_FORM = "application/x-www-form-urlencoded";
    
    public static final String INIT_PARAM_CONFIG = "com.wadpam.gaelic.Config";
    public static final String REQUEST_ATTR_PATHSTACK = "com.wadpam.gaelic.PathStack";
    public static final String REQUEST_ATTR_HANDLERNODE = "com.wadpam.gaelic.HandlerNode";
    public static final String REQUEST_ATTR_INTERCEPTORS = "com.wadpam.gaelic.Interceptors";
    public static final String REQUEST_ATTR_RESPONSEBODY = "com.wadpam.gaelic.ResponseBody";
    public static final String REQUEST_ATTR_RESPONSESTATUS = "com.wadpam.gaelic.ResponseStatus";
    
    public static final int ERROR_CODE_SERVLET_EXCEPTION = 1;
    public static final int ERROR_CODE_IO_EXCEPTION = 2;
    public static final int ERROR_CODE_ID_LONG = 3;
    
    public static final int ERROR_CODE_CRUD_BASE = 100;
    public static final int ERROR_CODE_ENTITY_BASE = 200;
    public static final int ERROR_CODE_LEAF_BASE = 300;
    public static final int ERROR_CODE_OAUTH2_PROVIDER_BASE = 900;
    public static final int ERROR_CODE_APPLICATION_BASE = 1000;
    
    public static final ObjectMapper MAPPER = new ObjectMapper();
    static {
        MAPPER.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
        MAPPER.getDeserializationConfig().set(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    
    static final Logger LOG = LoggerFactory.getLogger(GaelicServlet.class);
    
    private Node rootNode;

    @Override
    public void init(ServletConfig config) throws ServletException {
        final long start = System.currentTimeMillis();
        LOG.info("Initializing GaelicServlet named {}...", config.getServletName());
        super.init(config);
        
        // get the config classname
        final String classname = config.getInitParameter(INIT_PARAM_CONFIG);
        if (null == classname || "".equals(classname)) {
            throw new ServletException(INIT_PARAM_CONFIG);
        }
        
        // instantiate the config
        rootNode = loadAppConfig(classname, config);
        
        LOG.info("Successfully initialized {} ({}ms)", config.getServletName(), 
                System.currentTimeMillis()-start);
    }

    protected Node loadAppConfig(String classname, ServletConfig config) throws ServletException {
        try {
            Class clazz = getClass().getClassLoader().loadClass(classname.trim());
            GaelicConfig gaelicConfig = (GaelicConfig) clazz.newInstance();
            
            // initialize the config
            final Node root = gaelicConfig.init(this, config);
            root.init(config);
            
            return root;
        } catch (Exception ex) {
            throw new ServletException("Loading config", ex);
        }
    }
    
    protected void renderResponse(HttpServletRequest request, 
            HttpServletResponse response, 
            RestException exception) throws IOException {
        
        // for redirect and forward:
        if (response.isCommitted()) {
            LOG.trace("response already committed.");
            return;
        }
        
        Object responseBody;
        if (null != exception) {
            LOG.debug("Handling exception: {}", exception);
            
            if (RestException.STATUS_METHOD_NOT_ALLOWED == exception.getStatus() &&
                    exception instanceof MethodNotAllowedException) {
                // The response MUST include an Allow header containing a list of valid methods for the requested resource.
                String allow = ((MethodNotAllowedException) exception).getAllow().toString();
                allow = null != allow ? allow.substring(1, allow.length()-1) : null;
                LOG.info("Allow: {}", allow);
                response.setHeader("Allow", allow);
            }

            response.sendError(exception.getStatus(), exception.getMessage());
            responseBody = new JException(exception);
        }
        else {
            final Integer responseStatus = (Integer) request.getAttribute(REQUEST_ATTR_RESPONSESTATUS);
            response.setStatus(null != responseStatus ? responseStatus : 200);
            responseBody = request.getAttribute(REQUEST_ATTR_RESPONSEBODY);
        }
        
        if (null != responseBody) {
            
            // respond with XML or JSON?
            final String accepts = request.getHeader("Accept");
            String contentType = MEDIA_TYPE_JSON;
//            if (null != accepts && (accepts.contains("text/xml") || accepts.contains("application/xml"))) {
//                contentType = "application/xml";
//            }

            LOG.debug("Rendering body {} with Content-Type: {}", responseBody, contentType);
            response.setContentType(contentType);
            final PrintWriter writer = response.getWriter();
            // TODO: serialize XML
            MAPPER.writeValue(writer, responseBody);
            writer.flush();
            writer.close();
        }
    }

    /**
     * Invoke interceptors
     * @param request
     * @param response
     * @param handler
     * @param exception 
     */
    protected void afterCompletion(HttpServletRequest request, HttpServletResponse response, Node handler, Exception exception) {
        ArrayList<Interceptor> interceptors = (ArrayList<Interceptor>) request.getAttribute(GaelicServlet.REQUEST_ATTR_INTERCEPTORS);
        if (null != interceptors) {
            for (Interceptor i : interceptors) {
                try {
                    i.afterCompletion(request, response, handler, exception);
                }
                catch (ServletException ex) {
                    LOG.warn("afterCompletion " + i, ex);
                }
                catch (IOException ex) {
                    LOG.warn("afterCompletion " + i, ex);
                }
            }
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        final GaelicRequest request = new GaelicRequest(req);
        final String uri = request.getRequestURI();
        
        LOG.info("======= GaelicServlet processing {} {} ...", 
                request.getMethod(), uri);
        RestException exception = null;

        // stack the request URI
        final String ctxt = request.getContextPath();
        final LinkedList<String> pathStack = JKeyFactory.parsePath(uri, ctxt);
        Node handler = null;
        
        try {
            handler = rootNode.getServingNode(request, pathStack, 0);
            if (null != handler) {

                // populate and serve using handler
                request.setAttribute(REQUEST_ATTR_HANDLERNODE, handler);
                LOG.debug("------- Mapped {} {} to Handler {}", new Object[] {
                    request.getMethod(), uri, handler
                });

                rootNode.service(request, response);
            }
            else {
                exception = RestException.NOT_FOUND;
            }
        }
        catch (RestException rest) {
            exception = rest;
        }
        catch (ServletException ex) {
            exception = new RestException(500, ex.getMessage(), 
                    ERROR_CODE_SERVLET_EXCEPTION, null, null);
        }
        catch (IOException ex) {
            exception = new RestException(500, ex.getMessage(), 
                    ERROR_CODE_IO_EXCEPTION, null, null);
        }
        finally {
            renderResponse(request, response, exception);
            afterCompletion(request, response, handler, exception);
            LOG.info("+++++++ GaelicServlet done processing {} {} +++++++", 
                    request.getMethod(), uri);
        }
    }

    public static class GaelicRequest extends HttpServletRequestWrapper {
        private final String method;

        public GaelicRequest(HttpServletRequest request) {
            super(request);
            this.method = getEffectiveMethod(request);
        }

        protected static String getEffectiveMethod(HttpServletRequest request) {
            final String method = request.getMethod();
            final String _method = request.getParameter("_method");
            if (Node.METHOD_GET.equals(method) && null != _method) {
                return _method;
            }
            return method;
        }
    
        @Override
        public String getMethod() {
            return method;
        }
        
    }
}
