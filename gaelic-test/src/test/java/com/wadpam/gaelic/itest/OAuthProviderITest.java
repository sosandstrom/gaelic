package com.wadpam.gaelic.itest;

import com.wadpam.gaelic.net.NetworkTemplate;
import com.wadpam.gaelic.oauth.provider.json.JAccessTokenResponse;
import com.wadpam.gaelic.oauth.provider.json.Jo2pClient;
import com.wadpam.gaelic.oauth.provider.json.Jo2pProfile;
import com.wadpam.gaelic.oauth.provider.tree.AuthorizeEndpointLeaf;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;

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
//    protected static final String USERNAME = "john.doe@acme.com";

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
    
    protected static String createBasicAuthorization(String username, String password) {
        final String plain = String.format("%s:%s", username, password);
        final String encoded = Base64.encodeBase64String(plain.getBytes());
        return String.format("Basic %s", encoded);
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
    
    @Test
    public void testAuthorizeCodeGrantNoCredentials() {
        final String USERNAME = "testAuthorizeCodeGrantNoCredentials";
        LOG.info("+ {}() +", USERNAME);
        
        Jo2pClient client = createClient(USERNAME);
        String url = String.format("%s/authorize?response_type=code",
                BASE_URL);
        Map requestBody = NetworkTemplate.asMap("client_id", client.getId(), 
                "redirect_uri", BASE_REDIRECT_URI,
                "state", STATE);
        
        String actual = template.getForLocation(url, requestBody);
        LOG.info("actual URL: {}", actual);
        
        assertTrue("redirect contains login.html", actual.contains("login.html"));
        assertTrue("state", actual.contains(String.format("state=%s", STATE)));
    }
    
    @Test
    public void testAuthorizeCodeGrant() {
        final String USERNAME = "testAuthorizeCodeGrant";
        LOG.info("+ {}() +", USERNAME);
        
        Jo2pClient client = createClient(USERNAME);
        Jo2pProfile profile = createProfile(USERNAME);
        String url = String.format("%s/authorize?response_type=code",
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
        assertTrue("contains code", actual.contains("code="));
    }
    
    @Test
    public void testAccessTokenNoClientCredentials() {
        final String USERNAME = "testAccessTokenNoClientCredentials";
        LOG.info("+ {}() +", USERNAME);
        
        Jo2pClient client = createClient(USERNAME);
        Jo2pProfile profile = createProfile(USERNAME);
        String url = String.format("%s/authorize?response_type=code",
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
        assertTrue("contains code", actual.contains("code="));
        
        final String code = getParameterByName(actual, "code");
        url = String.format("%s/token", BASE_URL);        
        requestBody = NetworkTemplate.asMap("grant_type", "authorization_code",
                "code", code,
                "redirect_uri", BASE_REDIRECT_URI,
                "client_id", client.getId());
        try {
            JAccessTokenResponse token = template.post(url, requestBody, JAccessTokenResponse.class);
            fail("Expected 401 exception");
        }
        catch (RuntimeException expected) {
            assertTrue(expected.getMessage().contains("401"));
        }
    }
    
    @Test
    public void testAccessToken() {
        final String USERNAME = "testAccessToken";
        LOG.info("+ {}() +", USERNAME);
        
        Jo2pClient client = createClient(USERNAME);
        Jo2pProfile profile = createProfile(USERNAME);
        String url = String.format("%s/authorize?response_type=code",
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
        assertTrue("contains code", actual.contains("code="));
        
        final String code = getParameterByName(actual, "code");
        url = String.format("%s/token", BASE_URL);        
        requestBody = NetworkTemplate.asMap("grant_type", "authorization_code",
                "code", code,
                "redirect_uri", BASE_REDIRECT_URI,
                "client_id", client.getId());
        template.setAuthorization(createBasicAuthorization(client.getId(), client.getSecret()));
        
        // POST form-encoded instead of JSON
        Map<String, String> requestHeaders = NetworkTemplate.asMap(
                                    NetworkTemplate.CONTENT_TYPE, NetworkTemplate.MIME_FORM);
        JAccessTokenResponse token = template.post(url, requestHeaders, 
                requestBody, JAccessTokenResponse.class);
        template.setAuthorization(null);
        assertNotNull(token);
        assertNotNull(token.getAccess_token());
        assertNotNull(token.getRefresh_token());
    }
    
    @Test
    public void testAccessConfidentialProfile() {
        final String USERNAME = "testAccessConfidentialProfile";
        LOG.info("+ {}() +", USERNAME);
        
        Jo2pClient client = createClient(USERNAME);
        Jo2pProfile profile = createProfile(USERNAME);
        String url = String.format("%s/authorize?response_type=code",
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
        assertTrue("contains code", actual.contains("code="));
        
        final String code = getParameterByName(actual, "code");
        url = String.format("%s/token", BASE_URL);        
        requestBody = NetworkTemplate.asMap("grant_type", "authorization_code",
                "code", code,
                "redirect_uri", BASE_REDIRECT_URI,
                "client_id", client.getId());
        template.setAuthorization(createBasicAuthorization(client.getId(), client.getSecret()));
        
        // POST form-encoded instead of JSON
        Map<String, String> requestHeaders = NetworkTemplate.asMap(
                                    NetworkTemplate.CONTENT_TYPE, NetworkTemplate.MIME_FORM);
        JAccessTokenResponse token = template.post(url, requestHeaders, 
                requestBody, JAccessTokenResponse.class);
        template.setAuthorization(null);
        assertNotNull(token);
        assertNotNull(token.getAccess_token());
        assertNotNull(token.getRefresh_token());
        
        Jo2pProfile p = template.get(String.format("%s/profile/v10/me?access_token=%s", 
                BASE_URL, token.getAccess_token()), Jo2pProfile.class);
        assertEquals("ProfileId", profile.getId(), p.getId());
        
    }
    
    @Test
    public void testRefreshToken() {
        final String USERNAME = "testRefreshToken";
        LOG.info("+ {}() +", USERNAME);
        
        Jo2pClient client = createClient(USERNAME);
        Jo2pProfile profile = createProfile(USERNAME);
        String url = String.format("%s/authorize?response_type=code",
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
        assertTrue("contains code", actual.contains("code="));
        
        final String code = getParameterByName(actual, "code");
        url = String.format("%s/token", BASE_URL);        
        requestBody = NetworkTemplate.asMap("grant_type", "authorization_code",
                "code", code,
                "redirect_uri", BASE_REDIRECT_URI);
        template.setAuthorization(createBasicAuthorization(client.getId(), client.getSecret()));
        
        // POST form-encoded instead of JSON
        Map<String, String> requestHeaders = NetworkTemplate.asMap(
                                    NetworkTemplate.CONTENT_TYPE, NetworkTemplate.MIME_FORM);
        JAccessTokenResponse token = template.post(url, requestHeaders, 
                requestBody, JAccessTokenResponse.class);
        assertNotNull(token);
        assertNotNull(token.getAccess_token());
        assertNotNull(token.getRefresh_token());
        
        // refresh it right away
        requestBody = NetworkTemplate.asMap("grant_type", "refresh_token",
                "refresh_token", token.getRefresh_token());
        JAccessTokenResponse fresh = template.post(url, requestHeaders,
                requestBody, JAccessTokenResponse.class);
        template.setAuthorization(null);
        assertNotSame(token.getAccess_token(), fresh.getAccess_token());
        
        Jo2pProfile p = template.get(String.format("%s/profile/v10/me?access_token=%s", 
                BASE_URL, fresh.getAccess_token()), Jo2pProfile.class);
        assertEquals("ProfileId", profile.getId(), p.getId());
        
    }
    
    @Test
    public void testAuthorizeImplicit() {
        final String USERNAME = "testAuthorizeImplicit";
        LOG.info("+ {}() +", USERNAME);
        
        Jo2pClient client = createClient(USERNAME);
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
        assertTrue("contains token_type", actual.contains("token_type="));
    }
    
    @Test
    public void testNoCredentials() {
        final String USERNAME = "testNoCredentials";
        LOG.info("+ {}() +", USERNAME);
        Jo2pClient client = createClient(USERNAME);
        String url = String.format("%s/authorize?response_type=token",
                BASE_URL);
        Map requestBody = NetworkTemplate.asMap("client_id", client.getId(), 
                "redirect_uri", BASE_REDIRECT_URI,
                "state", STATE);
        
        String actual = template.getForLocation(url, requestBody);
        LOG.info("actual URL: {}", actual);
        
        assertTrue("starts with /oauth/login.html", actual.startsWith(AuthorizeEndpointLeaf.LOGIN_URI_DEFAULT));
        assertTrue("state", actual.contains(String.format("state=%s", STATE)));
        assertTrue("contains redirect_uri", actual.contains("redirect_uri="));
    }
    
    @Test
    public void testGetProfileNoToken() {
        final String USERNAME = "testGetProfileNoToken";
        LOG.info("+ {}() +", USERNAME);
        
        Jo2pClient client = createClient(USERNAME);
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
        assertTrue("contains token_type", actual.contains("token_type="));
    }
    
    @Test
    public void testGetProfile() {
        final String USERNAME = "testGetProfile";
        LOG.info("+ {}() +", USERNAME);
        
        Jo2pClient client = createClient(USERNAME);
        Jo2pProfile profile = createProfile(USERNAME);
        String url = String.format("%s/authorize?response_type=token",
                BASE_URL);
        Map requestBody = NetworkTemplate.asMap("client_id", client.getId(), 
                "redirect_uri", BASE_REDIRECT_URI,
                "state", STATE,
                "username", USERNAME,
                "password", USERNAME);
        
        String redirectUrl = template.getForLocation(url, requestBody);
        LOG.info("Redirect URL: {}", redirectUrl);
        
        String accessToken = getParameterByName(redirectUrl, "access_token");
        
        Jo2pProfile actual = template.get(String.format("%s/profile/v10/me?access_token=%s", 
                BASE_URL, accessToken), Jo2pProfile.class);
        assertEquals("ProfileId", profile.getId(), actual.getId());
    }

    public static String getParameterByName(String url, String name) {
        final String prefix = String.format("%s=", name);
        int beginIndex = url.indexOf(prefix) + prefix.length();
        int endIndex = url.indexOf('&', beginIndex);
        if (endIndex < 0) {
            endIndex = url.indexOf('#', beginIndex);
        }
        final String accessToken = -1 < endIndex ? 
                url.substring(beginIndex, endIndex) :
                url.substring(beginIndex);
        return accessToken;
    }
    
}
