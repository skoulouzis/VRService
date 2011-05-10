/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.scs.uva.vrsservice;

import nl.uva.vlet.vrs.VNode;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alogo
 */
public class ServiceTest {
    
    public ServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of openLocation method, of class Service.
     */
    @Test
    public void testOpenLocation() {
        System.out.println("openLocation");
        String uri = "file://localhost/home/alogo/maps/europe.bin";
        Service instance = new Service();
        NodeMetadata expResult = null;
        NodeMetadata result = instance.openLocation(uri);
        
        System.err.println("result: "+result);
        
//        System.err.println("result: "+result.getAttributeNames());
//        
//        String name = result.getHostname();
//        System.err.println("Name: "+name);
        
//        assertEquals(expResult, result);
    }
    
    
        @Test
    public void testOpenLocation2() {
        System.out.println("openLoc2");
        String uri = "file:///tmp";
        Service instance = new Service();
        VNode expResult = null;
        VNode result = instance.openLoc2(uri);
        
        
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of hello method, of class Service.
     */
    @Test
    public void testHello() {
        System.out.println("hello");
        String name = "Hello";
        Service instance = new Service();
        String expResult = "Hello " + name;
        String result = instance.hello(name);
        assertEquals(expResult, result);
    }
}
