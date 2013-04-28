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
    }

    @Test
    public void testNotFound() throws ServletException, IOException {
        request.setMethod("GET");
        request.setRequestURI("/api/anything/notFound");
        LOG.info("---------------- testNotFound() -------------------------------");

        servlet.service(request, response);
        assertEquals(404, response.getStatus());
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
}