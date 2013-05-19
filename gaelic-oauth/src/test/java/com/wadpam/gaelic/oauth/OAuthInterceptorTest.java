package com.wadpam.gaelic.oauth;

import com.wadpam.gaelic.GaelicServlet;
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
public class OAuthInterceptorTest {
    static final Logger LOG = LoggerFactory.getLogger(OAuthInterceptorTest.class);
    
    MockHttpServletRequest request;
    MockHttpServletResponse response;
    GaelicServlet servlet;
    
    public OAuthInterceptorTest() {
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
    public void testAuthenticatedHeader() throws ServletException, IOException {
        request.setMethod("GET");
        request.setRequestURI("/api/gaelic/endpoints");
        request.addHeader("Authorization", "Basic dXNlcm5hbWU6cGFzc3dvcmQ=");
        LOG.info("---------------- testAuthenticatedHeader() -----------------------");

        servlet.service(request, response);
        assertEquals(200, response.getStatus());
    }

    @Test
    public void testAuthenticatedParam() throws ServletException, IOException {
        request.setMethod("GET");
        request.setRequestURI("/api/gaelic/endpoints");
        request.setParameter("jBasic", "dXNlcm5hbWU6cGFzc3dvcmQ=");
        LOG.info("---------------- testAuthenticatedParam() -----------------------");

        servlet.service(request, response);
        assertEquals(200, response.getStatus());
    }

    @Test
    public void testAuthenticated_admin() throws ServletException, IOException {
        request.setMethod("GET");
        request.setRequestURI("/api/gaelic/_admin/task");
        LOG.info("---------------- testAuthenticated_admin() --------------------");

        servlet.service(request, response);
        assertEquals(200, response.getStatus());
    }

    @Test
    public void testAnonymous() throws ServletException, IOException {
        request.setMethod("GET");
        request.setRequestURI("/api/gaelic/public");
        LOG.info("---------------- testAnonymous() --------------------");

        servlet.service(request, response);
        assertEquals(200, response.getStatus());
    }

    @Test
    public void testAnonymousPOST() throws ServletException, IOException {
        request.setMethod("POST");
        request.setRequestURI("/api/gaelic/public");
        LOG.info("---------------- testAnonymousPOST() --------------------");

        servlet.service(request, response);
        assertEquals(401, response.getStatus());
    }

    @Test
    public void testUnauthorized() throws ServletException, IOException {
        request.setMethod("GET");
        request.setRequestURI("/api/gaelic/double");
        LOG.info("---------------- testUnauthorized() --------------------------");

        servlet.service(request, response);
        assertEquals(401, response.getStatus());
    }

    @Test
    public void testUnauthorizedFully() throws ServletException, IOException {
        request.setMethod("GET");
        request.setRequestURI("/api/gaelic/double");
        request.setParameter("jBasic", "dXNlcm5hbWU6cGFzc3dvcmQ=");
        LOG.info("---------------- testUnauthorizedFully() --------------------------");

        servlet.service(request, response);
        assertEquals(403, response.getStatus());
    }

}