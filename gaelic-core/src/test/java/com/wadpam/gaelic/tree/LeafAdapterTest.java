/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wadpam.gaelic.tree;

import com.wadpam.gaelic.*;
import com.wadpam.gaelic.json.JCursorPage;
import com.wadpam.gaelic.json.JDate;
import com.wadpam.gaelic.json.JKey;
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
public class LeafAdapterTest {
    static final Logger LOG = LoggerFactory.getLogger(GaelicServletTest.class);
    
    MockHttpServletRequest request;
    MockHttpServletResponse response;
    GaelicServlet servlet;
    
    public LeafAdapterTest() {
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
        LOG.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
    
    @Test
    public void testRootKey()  throws ServletException, IOException {
        LOG.info("---------------- testRootKey() -------------------------------");

        request.setMethod("GET");
        request.setRequestURI("/api/gaelic/adapter/DDate/123");
        
        servlet.service(request, response);
        assertEquals(200, response.getStatus());
        
        JKey key = (JKey) request.getAttribute(LeafAdapter.REQUEST_ATTR_JKEY);
        assertEquals("DDate", key.getKind());
        assertEquals("123", key.getId());
        assertNull(key.getParentKey());
    }
    
    @Test
    public void testParentKey()  throws ServletException, IOException {
        LOG.info("---------------- testParentKey() -------------------------------");

        request.setMethod("GET");
        request.setRequestURI("/api/gaelic/adapter/DDate/123/DParent/en");
        
        servlet.service(request, response);
        assertEquals(200, response.getStatus());
        
        JKey key = (JKey) request.getAttribute(LeafAdapter.REQUEST_ATTR_JKEY);
        assertEquals("DDate", key.getKind());
        assertEquals("123", key.getId());
        
        JKey parent = key.getParentKey();
        assertEquals("DParent", parent.getKind());
        assertEquals("en", parent.getId());
        assertNull(parent.getParentKey());
    }
    
    @Test
    public void testKindOverride()  throws ServletException, IOException {
        LOG.info("---------------- testKindOverride() -------------------------------");

        request.setMethod("GET");
        request.setRequestURI("/api/gaelic/adapter/datum/123/DParent/en");
        
        servlet.service(request, response);
        assertEquals(200, response.getStatus());
        
        JKey key = (JKey) request.getAttribute(LeafAdapter.REQUEST_ATTR_JKEY);
        assertEquals("DDate", key.getKind());
        assertEquals("123", key.getId());
        
        JKey parent = key.getParentKey();
        assertEquals("DParent", parent.getKind());
        assertEquals("en", parent.getId());
        assertNull(parent.getParentKey());
    }
    
    @Test
    public void testGetPageWithParent()  throws ServletException, IOException {
        LOG.info("---------------- testGetPageWithParent() -------------------------------");

        request.setMethod("GET");
        request.setRequestURI("/api/gaelic/adapter/datum/DParent/en");
        
        servlet.service(request, response);
        assertEquals(200, response.getStatus());
        
        JKey key = (JKey) request.getAttribute(LeafAdapter.REQUEST_ATTR_JKEY);
        assertEquals("DDate", key.getKind());
        assertNull(key.getId());
        
        JKey parent = key.getParentKey();
        assertEquals("DParent", parent.getKind());
        assertEquals("en", parent.getId());
        assertNull(parent.getParentKey());
    }
    

    static final class JDatePage extends JCursorPage<JDate> {}
}