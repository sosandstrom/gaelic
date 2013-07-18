package com.wadpam.gaelic.itest;

import com.wadpam.gaelic.net.NetworkTemplate;
import com.wadpam.gaelic.oauth.provider.json.Jo2pClient;
import com.wadpam.gaelic.oauth.provider.json.Jo2pProfile;
import com.wadpam.gaelic.oauth.provider.tree.AuthorizeLeaf;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Integration test for the CRUD leafs.
 * @author sosandtrom
 */
public class OAuthProviderITest {
    static final Logger LOG = LoggerFactory.getLogger(ProfileITest.class);

    protected static final String BASE_URL = "http://localhost:8485/oauth";
    protected static final String BASE_REDIRECT_URI = "http://host.domain.com:8282/context/myApp?param1=value1";

    protected static final String ACCESS_TOKEN = "John.I.Test";
    protected static final String CLIENT_ID = "s6BhdRkqt3";
    protected static final String STATE = "myNiceState";
    protected static final String USERNAME = "john.doe@acme.com";

    NetworkTemplate                         template;
    
    public OAuthProviderITest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        template = new NetworkTemplate();
        LOG.info("----------------- setUp() ---------------------------");
    }

    @After
    public void tearDown() {
    }

    protected Jo2pProfile createProfile(String email) {
        Jo2pProfile request = new Jo2pProfile();
        request.setEmail(email);
        request.setUsername(email);
        request.setPassword(email);
        Jo2pProfile actual = template.post(String.format("%s/profile/v10", BASE_URL), 
                request, Jo2pProfile.class);
        return actual;
    }
    
    protected Jo2pClient createClient(String name) {
        Jo2pClient request = new Jo2pClient();
        request.setDescription(String.format("Description of %s", name));
        request.setName(name);
        request.setRedirectUri(BASE_REDIRECT_URI);
        
        Jo2pClient actual = template.post(String.format("%s/client/v10", BASE_URL), 
                request, Jo2pClient.class);
        return actual;
    }
    
    @Test
    public void testNoClientId() {
        LOG.info("+ testNoClientId() +");
        
        String url = String.format("%s/authorize?response_type=code",
                BASE_URL);
        Map requestBody = NetworkTemplate.asMap("state", STATE,
                "redirect_uri", BASE_REDIRECT_URI);
        
        String actual = template.getForLocation(url, requestBody);
        LOG.info("actual URL: {}", actual);
        
        assertTrue("starts with redirect_uri", actual.startsWith(BASE_REDIRECT_URI));
        assertTrue("state", actual.contains(String.format("state=%s", STATE)));
        assertTrue("invalid_request", actual.contains("error=invalid_request"));
    }
    
//    @Test
//    public void testAuthorizeCodeGrant() {
//        LOG.info("+ testAuthorizeCodeGrant() +");
//        
//        String url = String.format("%s/authorize?response_type=code",
//                BASE_URL);
//        Map requestBody = NetworkTemplate.asMap("client_id", CLIENT_ID, 
//                "redirect_uri", BASE_REDIRECT_URI,
//                "state", STATE);
//        
//        String actual = template.getForLocation(url, requestBody);
//        LOG.info("actual URL: {}", actual);
//        
//        assertTrue("starts with redirect_uri", actual.startsWith(BASE_REDIRECT_URI));
//        assertTrue("state", actual.contains(String.format("state=%s", STATE)));
//        assertTrue("contains code", actual.contains("code=sTaTiCoDe"));
//    }
    
    @Test
    public void testAuthorizeImplicit() {
        LOG.info("+ testAuthorizeImplicit() +");
        
        Jo2pClient client = createClient("testAuthorizeImplicit");
        Jo2pProfile profile = createProfile(USERNAME);
        String url = String.format("%s/authorize?response_type=token",
                BASE_URL);
        Map requestBody = NetworkTemplate.asMap("client_id", client.getId(), 
                "redirect_uri", BASE_REDIRECT_URI,
                "state", STATE,
                "username", USERNAME,
                "password", USERNAME);
        
        String actual = template.getForLocation(url, requestBody);
        LOG.info("actual URL: {}", actual);
        
        assertTrue("starts with redirect_uri", actual.startsWith(BASE_REDIRECT_URI));
        assertTrue("state", actual.contains(String.format("state=%s", STATE)));
        assertTrue("contains token", actual.contains("access_token="));
        assertTrue("contains token_type", actual.contains("token_type=implicit"));
    }
    
    @Test
    public void testNoCredentials() {
        LOG.info("+ testNoCredentials() +");
        Jo2pClient client = createClient("testNoCredentials");
        String url = String.format("%s/authorize?response_type=token",
                BASE_URL);
        Map requestBody = NetworkTemplate.asMap("client_id", client.getId(), 
                "redirect_uri", BASE_REDIRECT_URI,
                "state", STATE);
        
        String actual = template.getForLocation(url, requestBody);
        LOG.info("actual URL: {}", actual);
        
        assertTrue("starts with /oauth/login.html", actual.startsWith(AuthorizeLeaf.LOGIN_URI_DEFAULT));
        assertTrue("state", actual.contains(String.format("state=%s", STATE)));
        assertTrue("contains redirect_uri", actual.contains("redirect_uri="));
    }
    
}
