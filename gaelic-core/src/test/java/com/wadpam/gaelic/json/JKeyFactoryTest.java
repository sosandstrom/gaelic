/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wadpam.gaelic.json;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sosandstrom
 */
public class JKeyFactoryTest {
    
    public JKeyFactoryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

     @Test
     public void testRootKey() {
         JKey actual = JKeyFactory.createKey("Kind/ID");
         assertEquals("Kind", actual.getKind());
         assertEquals("ID", actual.getId());
         assertNull(actual.getParentKey());
     }

     @Test
     public void testRootNullKey() {
         JKey actual = JKeyFactory.createKey("Kind/");
         assertEquals("Kind", actual.getKind());
         assertNull("ID", actual.getId());
         assertNull(actual.getParentKey());
     }

     @Test
     public void testParentKey() {
         JKey actual = JKeyFactory.createKey("/Kind/ID/Parent/name");
         assertEquals("Kind", actual.getKind());
         assertEquals("ID", actual.getId());
         
         JKey parent = actual.getParentKey();
         assertEquals("Parent", parent.getKind());
         assertEquals("name", parent.getId());
         assertNull(parent.getParentKey());
     }

     @Test
     public void testParentNullKey() {
         JKey actual = JKeyFactory.createKey("Kind/Parent/name");
         assertEquals("Kind", actual.getKind());
         assertNull("ID", actual.getId());

         JKey parent = actual.getParentKey();
         assertEquals("Parent", parent.getKind());
         assertEquals("name", parent.getId());
         assertNull(parent.getParentKey());
     }
     
     @Test
     public void testParentString() {
         final String EXPECTED = "/Kind/ID/Parent/name";
         JKey key = JKeyFactory.createKey(EXPECTED);
         String actual = JKeyFactory.createString(key);
         assertEquals(EXPECTED, actual);
     }

     @Test
     public void testParentNullString() {
         final String EXPECTED = "/Kind/Parent/name";
         JKey key = JKeyFactory.createKey(EXPECTED);
         String actual = JKeyFactory.createString(key);
         assertEquals(EXPECTED, actual);
     }
     
     
}