/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.scs.uva.vrsservice;

import java.util.ArrayList;
import nl.uva.vlet.Global;
import nl.uva.vlet.GlobalConfig;
import nl.uva.vlet.exception.VlException;
import nl.uva.vlet.util.bdii.ServiceInfo;
import nl.uva.vlet.util.bdii.ServiceInfo.ServiceInfoType;
import nl.uva.vlet.vrl.VRL;
import nl.uva.vlet.vrs.VNode;
import nl.uva.vlet.vrs.VRSClient;
import nl.uva.vlet.vrs.VRSContext;
import org.apache.axis2.databinding.types.URI.MalformedURIException;

/**
 *
 * @author alogo
 */
public class VRService implements IVRS {

    private VRSClient client;
    private static final boolean isInService = true;
    private final VRSContext vrsContext;

    public VRService() {

        GlobalConfig.setIsService(isInService);
        GlobalConfig.setInitURLStreamFactory(!isInService);
        Global.init();

        vrsContext = VRSContext.getDefault();
        this.client = new VRSClient(vrsContext);
    }

    private void debug(String msg) {
        System.err.println(this.getClass().getSimpleName() + ": " + msg);
    }

    @Override
    public org.apache.axis2.databinding.types.URI resolve(org.apache.axis2.databinding.types.URI baseURI, String relativeVRL) throws MalformedURIException, VlException {
            return  new org.apache.axis2.databinding.types.URI(VRSClient.resolve(new VRL(baseURI.toString()), relativeVRL).toURIString()) ;
    }

    @Override
    public INodeMetadata openLocation(org.apache.axis2.databinding.types.URI location) throws VlException {
        return new NodeMetadata(client.openLocation(new VRL(location.toString())));
    }

    @Override
    public INodeMetadata getNode(org.apache.axis2.databinding.types.URI location) throws VlException {
        return new NodeMetadata(client.getNode(new VRL(location.toString())));
    }

    @Override
    public ArrayList<ServiceInfo> queryServiceInfo(String vo, ServiceInfoType type) throws VlException {
        return client.queryServiceInfo(vo, type);
    }

    @Override
    public INodeMetadata[] list(org.apache.axis2.databinding.types.URI theURI) throws VlException {
        VNode[] nodes = client.list(new VRL(theURI.toString()));
        NodeMetadata[] metaNodes = new NodeMetadata[nodes.length];
        for (int i = 0; i < nodes.length; i++) {
            metaNodes[i] = new NodeMetadata(nodes[i]);
        }
        return metaNodes;

    }
}
