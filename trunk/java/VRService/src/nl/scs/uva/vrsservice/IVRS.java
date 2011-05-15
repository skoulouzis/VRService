/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.scs.uva.vrsservice;

import java.util.ArrayList;
import nl.uva.vlet.exception.VlException;
import nl.uva.vlet.util.bdii.ServiceInfo;
import org.apache.axis2.databinding.types.URI.MalformedURIException;

/**
 *
 * @author S. Koulouzis
 */
interface IVRS {

    public org.apache.axis2.databinding.types.URI resolve(org.apache.axis2.databinding.types.URI baseURI, String relativeVRL) throws MalformedURIException, VlException;

    /** Open remote location and return INodeMetadata. We have to use this URI. Other uri types have a reflcation problem  */
    public INodeMetadata openLocation(org.apache.axis2.databinding.types.URI location) throws VlException;

//    public INodeMetadata openLocation(String locationString);
    /**
     * Returns VNode associated with remote location. This method is mostly used
     * by the other get() methods so they can check the implementation type
     */
    public INodeMetadata getNode(org.apache.axis2.databinding.types.URI location) throws VlException;

    /**
     * VRS Method to Query BDII Service for specified ServiceInfoType and VO.  
     * @throws VlException 
     */
    public ArrayList<ServiceInfo> queryServiceInfo(String vo, ServiceInfo.ServiceInfoType type) throws VlException;

    /** 
     * Generic method to list the contents of a resource.
     * If resource is NOT composite, NULL will be returned. 
     */
    public INodeMetadata[] list(org.apache.axis2.databinding.types.URI theURI) throws VlException;
}
