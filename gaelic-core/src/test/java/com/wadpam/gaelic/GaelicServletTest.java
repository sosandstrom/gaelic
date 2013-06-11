/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wadpam.gaelic;

import com.wadpam.gaelic.tree.UnitTestInterceptor;
import java.io.IOException;
import javax.servlet.ServletException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletConfig;

import static org.junit.Assert.*;

/**
 *
 * @author os
 */
public class GaelicServletTest {
    static final Logger LOG = LoggerFactory.getLogger(GaelicServletTest.class);
    
    MockHttpServletRequest request;
    MockHttpServletResponse response;
    GaelicServlet servlet;
    
    public GaelicServletTest() {
        MockServletConfig servletConfig = new MockServletConfig("gaelic-test");
        servletConfig.addInitParameter(GaelicServlet.INIT_PARAM_CONFIG, UnitTestConfig.class.getName());
        servlet = new GaelicServlet();
        try {
            servlet.init(servletConfig);
        } catch (ServletException ex) {
            LOG.error("<init>", ex);
        }
    }
    
    @Before
    public void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetEndpoints() throws ServletException, IOException {
        request.setMethod("GET");
        request.setRequestURI("/api/gaelic/endpoints");
        LOG.info("---------------- testService() -------------------------------");

        servlet.service(request, response);
        assertEquals(200, response.getStatus());
//        assertTrue(0 < response.getContentLength());
        assertEquals("application/json", response.getContentType());
        assertEquals("{\"method\":\"GET\",\"uri\":\"/api/gaelic/endpoints\"}", response.getContentAsString());
        assertNull(request.getAttribute(UnitTestInterceptor.REQUEST_ATTR_INTERCEPTOR_PRE));
        
        Node handler = (Node) request.getAttribute(GaelicServlet.REQUEST_ATTR_HANDLERNODE);
        assertNotNull(handler);
        String domain = handler.getPathVariable("{domain}");
        assertEquals("gaelic", domain);
    }

    @Test
    public void testGetWithContext() throws ServletException, IOException {
        request.setMethod("GET");
        request.setRequestURI("/myapp-1-SNAPSHOT.war/api/gaelic/endpoints");
        request.setContextPath("/myapp-1-SNAPSHOT.war");
        LOG.info("---------------- testService() -------------------------------");

        servlet.service(request, response);
        assertEquals(200, response.getStatus());
//        assertTrue(0 < response.getContentLength());
        assertEquals("application/json", response.getContentType());
        assertEquals("{\"method\":\"GET\",\"uri\":\"/myapp-1-SNAPSHOT.war/api/gaelic/endpoints\"}", response.getContentAsString());
        assertNull(request.getAttribute(UnitTestInterceptor.REQUEST_ATTR_INTERCEPTOR_PRE));
        
        Node handler = (Node) request.getAttribute(GaelicServlet.REQUEST_ATTR_HANDLERNODE);
        assertNotNull(handler);
        String domain = handler.getPathVariable("{domain}");
        assertEquals("gaelic", domain);
    }

    @Test
    public void testNotFound() throws ServletException, IOException {
        request.setMethod("GET");
        request.setRequestURI("/api/anything/notFound");
        LOG.info("---------------- testNotFound() -------------------------------");

        servlet.service(request, response);
        assertEquals(404, response.getStatus());
        assertEquals("Not Found", response.getErrorMessage());
        assertTrue(response.getContentAsString().startsWith(
                "{\"code\":0,\"status\":404,\"message\":\"Not Found\",\"stackTrace\":\"com.wadpam.gaelic.exception.RestException.<clinit>:"));
    }

    @Test
    public void testInterceptorTrue() throws ServletException, IOException {
        request.setMethod("GET");
        request.setRequestURI("/api/gaelic/interceptor/true");
        LOG.info("---------------- testInterceptorTrue() -------------------------------");

        servlet.service(request, response);
        assertEquals(200, response.getStatus());
        assertEquals(request.getRequestURI(), request.getAttribute(UnitTestInterceptor.REQUEST_ATTR_INTERCEPTOR_PRE));
        assertEquals(request.getRequestURI(), request.getAttribute(UnitTestInterceptor.REQUEST_ATTR_INTERCEPTOR_POST));
        assertEquals(request.getRequestURI(), request.getAttribute(UnitTestInterceptor.REQUEST_ATTR_INTERCEPTOR_AFTER));
    }

    @Test
    public void testInterceptorFalse() throws ServletException, IOException {
        request.setMethod("GET");
        request.setRequestURI("/api/gaelic/interceptor/false");
        LOG.info("---------------- testInterceptorFalse() -------------------------------");

        servlet.service(request, response);
        assertEquals(403, response.getStatus());
        assertEquals(request.getRequestURI(), request.getAttribute(UnitTestInterceptor.REQUEST_ATTR_INTERCEPTOR_PRE));
        assertNull(request.getAttribute(UnitTestInterceptor.REQUEST_ATTR_INTERCEPTOR_POST));
        assertNull(request.getAttribute(UnitTestInterceptor.REQUEST_ATTR_INTERCEPTOR_AFTER));
    }

    @Test
    public void testJsonpPost() throws ServletException, IOException {
        request.setMethod("GET");
        request.setRequestURI("/api/gaelic/endpoints");
        request.setParameter("_method", "POST");
        request.setQueryString("?_method=POST");
        LOG.info("---------------- testJsonpPost() -------------------------------");

        servlet.service(request, response);
        assertEquals(200, response.getStatus());
//        assertTrue(0 < response.getContentLength());
        assertEquals("application/json", response.getContentType());
        assertEquals("{\"method\":\"POST\",\"uri\":\"/api/gaelic/endpoints\"}", response.getContentAsString());
        assertNull(request.getAttribute(UnitTestInterceptor.REQUEST_ATTR_INTERCEPTOR_PRE));
        
        Node handler = (Node) request.getAttribute(GaelicServlet.REQUEST_ATTR_HANDLERNODE);
        assertNotNull(handler);
        String domain = handler.getPathVariable("{domain}");
        assertEquals("gaelic", domain);
    }

}