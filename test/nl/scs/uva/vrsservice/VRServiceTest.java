/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.scs.uva.vrsservice;

import java.util.ArrayList;
import nl.uva.vlet.util.bdii.ServiceInfo.ServiceInfoType;
import org.apache.axis2.databinding.types.URI;
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
public class VRServiceTest {
    
    public VRServiceTest() {
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

//    /**
//     * Test of resolve method, of class VRService.
//     */
//    @Test
//    public void testResolve() throws Exception {
//        System.out.println("resolve");
//        URI baseURI = null;
//        String relativeVRL = "";
//        VRService instance = new VRService();
//        java.net.URI expResult = null;
//        java.net.URI result = instance.resolve(baseURI, relativeVRL);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of openLocation method, of class VRService.
     */
    @Test
    public void testOpenLocation() throws Exception {
        System.out.println("openLocation");
        URI location = new URI("file://localhost/tmp");
        VRService instance = new VRService();
        INodeMetadata expResult = null;
        INodeMetadata result = instance.openLocation(location);
        
        result.getAttributes();
        
    }

//    /**
//     * Test of getNode method, of class VRService.
//     */
//    @Test
//    public void testGetNode() throws Exception {
//        System.out.println("getNode");
//        URI location = null;
//        VRService instance = new VRService();
//        INodeMetadata expResult = null;
//        INodeMetadata result = instance.getNode(location);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of queryServiceInfo method, of class VRService.
//     */
//    @Test
//    public void testQueryServiceInfo() throws Exception {
//        System.out.println("queryServiceInfo");
//        String vo = "";
//        ServiceInfoType type = null;
//        VRService instance = new VRService();
//        ArrayList expResult = null;
//        ArrayList result = instance.queryServiceInfo(vo, type);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of list method, of class VRService.
//     */
//    @Test
//    public void testList() throws Exception {
//        System.out.println("list");
//        URI theURI = null;
//        VRService instance = new VRService();
//        INodeMetadata[] expResult = null;
//        INodeMetadata[] result = instance.list(theURI);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
