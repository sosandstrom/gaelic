/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wadpam.gaelic.oauth.provider.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author os
 */
public class ProviderServiceTest {
    
    public ProviderServiceTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of encryptPassword method, of class ProviderService.
     */
    @Test
    public void testEncryptPassword() {
        System.out.println("encryptPassword");
        String correct = "Correc8t", similar = "correc8t";
        long salt = 42L, sugar = 43L;
        String expResult = "ab71279df4c69cedc824d14835eab78101013bd0";
        String result = ProviderService.encryptPassword(correct, salt);
        assertEquals(expResult, result);

        result = ProviderService.encryptPassword(correct, sugar);
        assertNotSame(expResult, result);

        result = ProviderService.encryptPassword(similar, salt);
        assertNotSame(expResult, result);

        result = ProviderService.encryptPassword(similar, sugar);
        assertNotSame(expResult, result);
    }

}