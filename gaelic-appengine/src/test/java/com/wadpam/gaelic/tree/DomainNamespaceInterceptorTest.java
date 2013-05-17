/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wadpam.gaelic.tree;

import com.google.appengine.api.NamespaceManager;
import com.google.appengine.tools.development.testing.LocalMemcacheServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.wadpam.gaelic.UnitTestConfig;
import com.wadpam.gaelic.*;
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
public class DomainNamespaceInterceptorTest {
    static final Logger LOG = LoggerFactory.getLogger(DomainNamespaceInterceptorTest.class);
    final LocalServiceTestHelper helper = new LocalServiceTestHelper(
            new LocalMemcacheServiceTestConfig());
    
    MockHttpServletRequest request;
    MockHttpServletResponse response;
    GaelicServlet servlet;
    
    public DomainNamespaceInterceptorTest() {
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
        helper.setUp();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        LOG.info("--------------------------------------------------------------");
    }
    
    @After
    public void tearDown() {
        LOG.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        helper.tearDown();
    }
    
    @Test
    public void testGaelicDomain()  throws ServletException, IOException {
        LOG.info("---------------- testGaelicDomain() ------------------------------");

        request.setMethod("GET");
        request.setRequestURI("/api/gaelic/namespace");
        
        servlet.service(request, response);
        assertEquals(200, response.getStatus());
        assertEquals("\"gaelic\"", response.getContentAsString());
    }
    
    @Test
    public void testExceptionDomain()  throws ServletException, IOException {
        LOG.info("---------------- testExceptionDomain() ------------------------------");

        request.setMethod("GET");
        request.setRequestURI("/api/exception/namespace");
        
        servlet.service(request, response);
        assertEquals(409, response.getStatus());
        assertNull(NamespaceManager.get());
        assertEquals("exception", request.getAttribute(GaelicServlet.REQUEST_ATTR_RESPONSEBODY));
        
    }
    
    @Test
    public void testDefaultDomain()  throws ServletException, IOException {
        LOG.info("---------------- testDefaultDomain() ------------------------------");

        request.setMethod("GET");
        request.setRequestURI("/api/default/namespace");
        
        servlet.service(request, response);
        assertEquals(200, response.getStatus());
        assertEquals("", response.getContentAsString());
    }
    
}