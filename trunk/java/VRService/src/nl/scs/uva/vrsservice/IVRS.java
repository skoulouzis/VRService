/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.scs.uva.vrsservice;

import java.net.URI;
import java.util.ArrayList;
import nl.uva.vlet.exception.VlURISyntaxException;
import nl.uva.vlet.util.bdii.ServiceInfo;
import nl.uva.vlet.vrl.VRL;

/**
 *
 * @author alogo
 */
interface IVRS {

    public URI resolve(URI baseURI, String relativeVRL);

    /** Open remote location and return VNode */
    public INodeMetadata openLocation(URI location);

//    public INodeMetadata openLocation(String locationString);

    /**
     * Returns VNode associated with remote location. This method is mostly used
     * by the other get() methods so they can check the implementation type
     */
    public INodeMetadata getNode(URI location);

    /**
     * VRS Method to Query BDII Service for specified ServiceInfoType and VO.  
     * @throws VlException 
     */
    public ArrayList<ServiceInfo> queryServiceInfo(String vo, ServiceInfo.ServiceInfoType type);

    /** 
     * Generic method to list the contents of a resource.
     * If resource is NOT composite, NULL will be returned. 
     */
    public INodeMetadata[] list(URI theURI);
}
