/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wadpam.gaelic.net;

import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author os
 */
public class NetworkTemplateTest {
    
    public NetworkTemplateTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

     @Test
     public void testExpandUrl() {
        Map paramMap = NetworkTemplate.asMap("grant_type", "authorization_code",
                                    "code", "GreatCode",
                                    "redirect_uri", "http://host.domain/query?param1=value1#fragment2=value2",
                                    "client_id", "abc123");
         
         String actual = NetworkTemplate.expandUrl("", paramMap);
         assertEquals("?client_id=abc123&code=GreatCode&grant_type=authorization_code&redirect_uri=http%3A%2F%2Fhost.domain%2Fquery%3Fparam1%3Dvalue1%23fragment2%3Dvalue2", actual);
     }
}