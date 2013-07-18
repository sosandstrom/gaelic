package com.wadpam.gaelic.itest;

import com.wadpam.gaelic.oauth.provider.json.Jo2pProfile;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Integration test for the CRUD leafs.
 * @author sosandtrom
 */
public class ProfileITest {
    static final Logger LOG = LoggerFactory.getLogger(ProfileITest.class);

    protected static final String                  BASE_URL       = "http://localhost:8485/oauth/";
    
    RestTemplate                         template;
    public ProfileITest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        template = new RestTemplate();
        LOG.info("----------------- setUp() ---------------------------");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreateProfile() {
        LOG.info("+ testCreateProfile():");
        final String EMAIL = "+85517222165";
        Jo2pProfile actual = createProfile(EMAIL);
        assertNotNull("Assigned Profile ID", actual.getId());
        assertNull("Exposed password", actual.getPassword());
        assertEquals("Created email", EMAIL, actual.getEmail());
        assertEquals("Created username", EMAIL, actual.getUsername());
    }
    
    protected Jo2pProfile createProfile(String email) {
        Jo2pProfile request = new Jo2pProfile();
        request.setEmail(email);
        request.setUsername(email);
        request.setPassword(email);
        Jo2pProfile actual = template.postForObject(BASE_URL + "profile/v10", request, Jo2pProfile.class);
        return actual;
    }

}
