/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wadpam.gaelic.tree;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.wadpam.gaelic.GaelicServlet;
import com.wadpam.gaelic.UnitTestConfig;
import com.wadpam.gaelic.json.JCursorPage;
import com.wadpam.gaelic.json.JEntity;
import com.wadpam.gaelic.tree.CrudLeaf;
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
public class EntityLeafTest {
    static final Logger LOG = LoggerFactory.getLogger(EntityLeafTest.class);

    final LocalServiceTestHelper helper = new LocalServiceTestHelper(
            new LocalDatastoreServiceTestConfig()
                .setDefaultHighRepJobPolicyUnappliedJobPercentage(0));
    
    MockHttpServletRequest request;
    MockHttpServletResponse response;
    GaelicServlet servlet;
    
    public EntityLeafTest() {
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
    
    protected JEntity doCreate(long startDate) throws ServletException, IOException {
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        req.setMethod("POST");
        req.setRequestURI("/api/gaelic/entity/Empl");
        req.setContentType("application/json");
        
//        final String parentKey = "{\"kind\":\"Org\",\"id\":123}";
        
        String properties = String.format("{\"millis\":13705,\"startDate\":%d}", startDate);
        String propertyTypes = "{\"startDate\":\"java.util.Date\"}";
        
//        String content = String.format("{\"kind\":\"Empl\",\"parentKey\":%s,\"properties\":%s,\"propertyTypes\":%s}",
//                parentKey, properties, propertyTypes);
        String content = String.format("{\"kind\":\"Empl\",\"properties\":%s,\"propertyTypes\":%s}",
                properties, propertyTypes);
           
        req.setContent(content.getBytes());
        
        servlet.service(req, res);
        
        assertEquals(201, res.getStatus());
        final JEntity body = GaelicServlet.MAPPER.readValue(res.getContentAsString(), JEntity.class);
        assertNotNull(res.getContentAsString());
        
        assertNull(body.getParentKey());
        assertNotNull(body.getId());
        assertEquals("Empl", body.getKind());
        assertEquals((Integer)13705, body.getProperties().get("millis"));
        assertEquals(startDate, body.getProperties().get("startDate"));
        return body;
    }
    
    @Test
    public void testCreate()  throws ServletException, IOException {
        LOG.info("---------------- testCreate() -------------------------------");

        JEntity empl = doCreate(1370503068166L);

        request.setMethod("GET");
        request.setRequestURI(String.format("/api/gaelic/entity/Empl/%s", empl.getId()));
        
        servlet.service(request, response);
        assertEquals(200, response.getStatus());
        LOG.info("response: {}", response.getContentAsString());
        final JEntity body = GaelicServlet.MAPPER.readValue(response.getContentAsString(), JEntity.class);
        assertEquals(empl.getId(), body.getId());
        assertEquals((Long)1370503068166L, body.getProperties().get("startDate"));
    }

    @Test
    public void testDelete()  throws ServletException, IOException {
        LOG.info("---------------- testDelete() -------------------------------");

        JEntity empl = doCreate(1370503068165L);

        request.setMethod("DELETE");
        request.setRequestURI(String.format("/api/gaelic/entity/Empl/%s", empl.getId()));
        
        servlet.service(request, response);
        assertEquals(200, response.getStatus());

        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        request.setMethod("GET");
        request.setRequestURI(String.format("/api/gaelic/entity/Empl/%s", empl.getId()));
        
        servlet.service(request, response);
        assertEquals(404, response.getStatus());
    }
    
    @Test
    public void testGetPage() throws ServletException, IOException {
//        JDate jDate = doCreate();
//        assertEquals(201, response.getStatus());
//
//        request.setMethod("GET");
//        request.setRequestURI("/api/gaelic/crud/v10");
//        LOG.info("---------------- testGetPage() -------------------------------");
//
//        servlet.service(request, response);
//        assertEquals(200, response.getStatus());
//        
//        assertNull(request.getAttribute(CrudLeaf.REQUEST_ATTR_FILENAME));
//        
//        assertNotNull(response.getContentAsString());
//        JDatePage page = GaelicServlet.MAPPER.readValue(response.getContentAsByteArray(), JDatePage.class);
//        assertNull(page.getCursorKey());
//        assertEquals(1, page.getItems().size());
//        assertEquals(1, page.getTotalSize().intValue());
    }

    @Test
    public void testGetPageEmptyFilename() throws ServletException, IOException {
//        request.setMethod("GET");
//        request.setRequestURI("/api/gaelic/crud/v10/");
//        LOG.info("---------------- testGetPageEmptyFilename() ------------------");
//
//        servlet.service(request, response);
//        assertEquals(200, response.getStatus());
//        
//        Node handler = (Node) request.getAttribute(GaelicServlet.REQUEST_ATTR_HANDLERNODE);
//        assertNotNull(handler);
//        String domain = handler.getPathVariable("{domain}");
//        assertEquals("gaelic", domain);
//        
//        assertNull(request.getAttribute(CrudLeaf.REQUEST_ATTR_FILENAME));
    }

    @Test
    public void testNotFound() throws ServletException, IOException {
        request.setMethod("GET");
        request.setRequestURI("/api/gaelic/entity/Empl/9999");
        LOG.info("---------------- testNotFound() ------------------------------");

        servlet.service(request, response);
        assertEquals(404, response.getStatus());
        assertEquals("Not Found", response.getErrorMessage());
//        assertEquals("{:", response.getContentAsString());
        assertTrue(response.getContentAsString().startsWith(
                "{\"code\":204,\"status\":404,\"message\":\"Not Found\",\"developerMessage\":\"JKey{Empl(9999)}\",\"stackTrace\":\"com.wadpam.gaelic.tree.EntityLeaf.getResourceByKey:"));
    }

    @Test
    public void testIdNotLong() throws ServletException, IOException {
//        request.setMethod("GET");
//        request.setRequestURI("/api/anything/crud/v10/notLong");
//        LOG.info("---------------- testIdNotLong() ------------------------------");
//
//        servlet.service(request, response);
//        assertEquals(400, response.getStatus());
//        assertEquals("Bad Request", response.getErrorMessage());
////        assertEquals("{}", response.getContentAsString());
//        assertTrue(response.getContentAsString().startsWith(
//                "{\"code\":3,\"status\":400,\"message\":\"Bad Request\",\"developerMessage\":\"notLong\",\"stackTrace\":\"com.wadpam.gaelic.tree.CrudLeaf.getId:"));
    }

    @Test
    public void testUpdate()  throws ServletException, IOException {
        LOG.info("---------------- testUpdate() -------------------------------");

        JEntity empl = doCreate(1370503068167L);

        final long millis = System.currentTimeMillis();
        empl.getProperties().put("startDate", millis);
        
        request.setMethod("POST");
        request.setRequestURI(String.format("/api/gaelic/entity/Empl/%s", empl.getId()));
        request.setContentType("application/json");
        byte[] requestBody = GaelicServlet.MAPPER.writeValueAsBytes(empl);
        request.setContent(requestBody);
        
        servlet.service(request, response);
        assertEquals(200, response.getStatus());
        assertNotNull(response.getContentAsString());
        
        JEntity actual = GaelicServlet.MAPPER.readValue(response.getContentAsString(), JEntity.class);
        
        assertEquals(empl.getId(), actual.getId());
        assertEquals((Long)millis, actual.getProperties().get("startDate"));
        
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        request.setMethod("GET");
        request.setRequestURI(String.format("/api/gaelic/entity/Empl/%s", empl.getId()));
        
        servlet.service(request, response);
        assertEquals(200, response.getStatus());
        assertNotNull(response.getContentAsString());
        
        JEntity read = GaelicServlet.MAPPER.readValue(response.getContentAsString(), JEntity.class);
        
        assertEquals(empl.getId(), read.getId());
        assertEquals((Long)millis, read.getProperties().get("startDate"));
    }

//    static final class JDatePage extends JCursorPage<DDate> {}
}