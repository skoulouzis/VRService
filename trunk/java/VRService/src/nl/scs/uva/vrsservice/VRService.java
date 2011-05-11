/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.scs.uva.vrsservice;

import java.net.URI;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.uva.vlet.Global;
import nl.uva.vlet.GlobalConfig;
import nl.uva.vlet.exception.VlException;
import nl.uva.vlet.exception.VlURISyntaxException;
import nl.uva.vlet.util.bdii.ServiceInfo;
import nl.uva.vlet.util.bdii.ServiceInfo.ServiceInfoType;
import nl.uva.vlet.vrl.VRL;
import nl.uva.vlet.vrs.VNode;
import nl.uva.vlet.vrs.VRSClient;
import nl.uva.vlet.vrs.VRSContext;

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
    public URI resolve(URI baseURI, String relativeVRL) {
        try {
            return VRSClient.resolve(new VRL(baseURI), relativeVRL).toURI();
        } catch (VlURISyntaxException ex) {
            Logger.getLogger(VRService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public INodeMetadata openLocation(URI location) {
        try {
            return new NodeMetadata(client.openLocation(new VRL(location)));
        } catch (VlException ex) {
            Logger.getLogger(VRService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

//    @Override
//    public INodeMetadata openLocation(String locationString) {
//        try {
//            return new NodeMetadata(client.openLocation(locationString));
//        } catch (VlException ex) {
//            Logger.getLogger(VRService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }

    @Override
    public INodeMetadata getNode(URI location) {
        try {
            return new NodeMetadata(client.getNode(new VRL(location)));
        } catch (VlException ex) {
            Logger.getLogger(VRService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<ServiceInfo> queryServiceInfo(String vo, ServiceInfoType type) {
        try {
            return client.queryServiceInfo(vo, type);
        } catch (VlException ex) {
            Logger.getLogger(VRService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public INodeMetadata[] list(URI theURI) {
        try {
            VNode[] nodes = client.list(new VRL(theURI));
            NodeMetadata[] metaNodes = new NodeMetadata[nodes.length];
            for (int i = 0; i < nodes.length; i++) {
                metaNodes[i] = new NodeMetadata(nodes[i]);
            }
            return metaNodes;
        } catch (VlException ex) {
            Logger.getLogger(VRService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
