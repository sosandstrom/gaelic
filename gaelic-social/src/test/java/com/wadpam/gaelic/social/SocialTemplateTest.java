/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wadpam.gaelic.social;

import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author os
 */
public class SocialTemplateTest {
    
    static final String ACCESS_TOKEN = 
            "CAAGatmfrg48BAFPZBTcHZBLRXfTknsdhOzr2LVF5KNOoPqsp4LQnhD3UW27nR4LGPtzZA7zswHrM02vi2W4GVqf4UX8QaoxKvud6d2uhiXfZCZAm0oxG2JtmR9ZArAM4lhFYw2UN6HW6K1kZCtictPjWQ8iPCZAG2xkHrZAqWqf6XuwZDZD";
    
    SocialTemplate template;
    
    public SocialTemplateTest() {
    }
    
    @Before
    public void setUp() {
        template = new SocialTemplate(ACCESS_TOKEN);
    }
    
    @After
    public void tearDown() {
    }

//    @Test
//    public void testGetProfile() throws IOException {
//        SocialProfile actual = template.getProfile();
//        assertNotNull(actual);
//        assertNotNull(actual.getUsername());
//    }
}