/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sosandstrom
 */
public class GaelicServlet extends HttpServlet {
    
    public static final String INIT_PARAM_CONFIG = "com.wadpam.gaelic.Config";
    public static final String REQUEST_ATTR_PATHSTACK = "com.wadpam.gaelic.PathStack";
    public static final String REQUEST_ATTR_HANDLERNODE = "com.wadpam.gaelic.HandlerNode";
    public static final String REQUEST_ATTR_RESPONSEBODY = "com.wadpam.gaelic.ResponseBody";
    public static final String REQUEST_ATTR_RESPONSESTATUS = "com.wadpam.gaelic.ResponseStatus";
    
    protected static final ObjectMapper MAPPER = new ObjectMapper();
    static {
        MAPPER.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
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
    
    protected static LinkedList<String> parsePath(HttpServletRequest request) {
        final LinkedList<String> pathStack = new LinkedList<String>();
        final String uri = request.getRequestURI();
        for (String p : uri.split("/")) {
            if (!"".equals(p)) {
                pathStack.add(p);
            }
        }
        request.setAttribute(REQUEST_ATTR_PATHSTACK, pathStack);
        return pathStack;
    }

    protected void renderResponse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // respond with XML or JSON?
        final Integer responseStatus = (Integer) request.getAttribute(REQUEST_ATTR_RESPONSESTATUS);
        response.setStatus(null != responseStatus ? responseStatus : 200);
        final Object responseBody = request.getAttribute(REQUEST_ATTR_RESPONSEBODY);
        if (null != responseBody) {
            final String accepts = request.getHeader("Accept");
            String contentType = "application/json";
            if (null != accepts && (accepts.contains("text/xml") || accepts.contains("application/xml"))) {
                contentType = "application/xml";
            }

            LOG.debug("Rendering body with Content-Type: {}", contentType);
            response.setContentType(contentType);
            final PrintWriter writer = response.getWriter();
            // TODO: serialize XML
            MAPPER.writeValue(writer, responseBody);
            writer.flush();
            writer.close();
        }
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // stack the request URI
        final LinkedList<String> pathStack = parsePath(request);

        final Node handler = rootNode.getServingNode(request, pathStack, 0);
        
        if (null != handler) {
            
            // populate and serve using handler
            request.setAttribute(REQUEST_ATTR_HANDLERNODE, handler);
            LOG.debug("Mapped {} {} to Handler {}", new Object[] {
                request.getMethod(), request.getRequestURI(), handler
            });
            
            rootNode.service(request, response);
        }
        else {
            request.setAttribute(GaelicServlet.REQUEST_ATTR_RESPONSESTATUS, 404);
        }
        
        renderResponse(request, response);
    }

}
