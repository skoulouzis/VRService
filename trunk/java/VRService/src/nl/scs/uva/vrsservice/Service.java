/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.scs.uva.vrsservice;

import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.uva.vlet.Global;
import nl.uva.vlet.GlobalConfig;
import nl.uva.vlet.data.VAttribute;
import nl.uva.vlet.exception.VlException;
import nl.uva.vlet.vrs.VNode;
import nl.uva.vlet.vrs.VRS;
import nl.uva.vlet.vrs.VRSClient;
import nl.uva.vlet.vrs.VRSContext;

/**
 *
 * @author alogo
 */
public class Service {

    private VRSClient client;
    private final boolean isInService = true;
    private final VRSContext vrsContext;

    public Service() {

        GlobalConfig.setIsService(isInService);
        GlobalConfig.setInitURLStreamFactory(!isInService);
        
        Global.init();

        vrsContext = VRSContext.getDefault();
        this.client = new VRSClient(vrsContext);
    }

    public NodeMetadata openLocation(String uri) {
        try {
            debug("------------- URI: "+uri);
            VNode node = client.openLocation(uri);
            debug("New Node: "+node.getVRL());
            NodeMetadata meta = new NodeMetadata(node);

            return meta;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public VNode openLoc2(String uri) {
        VNode node = null;
        try {
            System.err.println("Calling openLoc2");
            node = client.openLocation(uri);
            String type = node.getMimeType();


            System.err.println("Type: " + type);

        } catch (Exception ex) {
            System.err.println("Something happend!!!!!");
            System.err.println(ex.getMessage());
        }
        return node;
    }

    public String hello(String name) {

        System.err.println("Name is " + name);

        return "Hello " + name;
    }

    private void debug(String msg) {
         System.err.println(this.getClass().getSimpleName() + ": " + msg);
    }
}
