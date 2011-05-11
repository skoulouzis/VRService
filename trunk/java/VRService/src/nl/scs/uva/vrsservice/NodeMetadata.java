/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.scs.uva.vrsservice;

import java.net.URI;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.uva.vlet.Global;
import nl.uva.vlet.GlobalConfig;
import nl.uva.vlet.data.VAttribute;
import nl.uva.vlet.data.VAttributeSet;
import nl.uva.vlet.exception.VlException;
import nl.uva.vlet.exception.VlIOException;
import nl.uva.vlet.exception.VlURISyntaxException;
import nl.uva.vlet.util.MimeTypes;
import nl.uva.vlet.vrl.VRL;
import nl.uva.vlet.vrs.VNode;

/**
 *
 * @author alogo
 */
public class NodeMetadata implements INodeMetadata {

    private final VNode node;
    private static int parentLevel = 0;

    public NodeMetadata(VNode node) {
        debug("-----------------New Node " + this.hashCode() + " ----------------");
        this.node = node;
        debug("Node: " + node.hashCode());
        debug("VRL: " + node.getVRL());
    }

    @Override
    public String getName() {
        String name = node.getName();
        debug("getName: " + name);
        return name;
    }

    @Override
    public URI getURI() {
        try {
            URI uri = node.getURI();
            debug("getURI: " + uri);
            return uri;
        } catch (Exception ex) {
            System.err.println("Cant get URI!");

        }

        return null;
    }

    @Override
    public String getExtension() {
        String extention = node.getExtension();
        debug("getExtension: " + extention);
        return extention;
    }

    @Override
    public VAttribute getAttribute(String name) {
        VAttribute attributes = null;
        try {
            attributes = node.getAttribute(name);
        } catch (VlException ex) {
            Logger.getLogger(NodeMetadata.class.getName()).log(Level.SEVERE, null, ex);
        }
        return attributes;
    }

//    @Override
//    public VRL getVRL() {
//        //Takes a long time!!!!!!
//        VRL vrl = node.getVRL();
//        debug("getVRL: " + vrl);
//        return vrl;
//    }
//    @Override
//    public URL getURL() {
//        try {
//            return node.getURL();
//        } catch (Exception ex) {
//            System.err.println("Cant get URL!");
//        }
//        return null;
//    }
    @Override
    public long getNodeID() {
        long nodeId = node.getNodeID();
        debug("getNodeID: " + nodeId);
        return nodeId;
    }

    @Override
    public String getPath() {
        String path = node.getPath();
        debug("getPath: " + path);
        return path;
    }

    @Override
    public String getHostname() {
        String hostname = node.getHostname();
        debug("Hostname: " + hostname);
        return hostname;
    }

    @Override
    public int getPort() {
        int port = node.getPort();
        debug("getPort: " + port);
        return port;
    }

    @Override
    public String getBasename() {
        String baseName = node.getBasename();
        debug("getBasename: " + baseName);
        return baseName;
    }

    @Override
    public String getMimeType() {
        String type = null;
        try {
            MimeTypes l;

            type = node.getMimeType();
            debug("getMimeType: " + type);
        } catch (Exception ex) {
            System.err.println("I dont know this type!!!!!!!!");
        }
        return type;
    }

    @Override
    public String getCharSet() {
        try {
            String charSet = node.getCharSet();
            debug("getCharSet: " + charSet);
            return charSet;
        } catch (Exception ex) {
            System.err.println("Cant get Char set!");
        }
        return null;
    }

    @Override
    public boolean isComposite() {
        boolean composite = node.isComposite();
        debug("isComposite: " + composite);
        return composite;
    }

    @Override
    public String[] getAttributeNames() {
        String[] attrNames = node.getAttributeNames();
        for (String name : attrNames) {
            debug("getAttributeNames: " + name);
        }
        return attrNames;
    }

//    @Override
//    public VAttribute[] getAttributes() {
//        //Dug with getDateValue
//        try {
//            VAttribute[] attributes = node.getAttributes();
//            debug("getAttributes: " + attributes);
//
//            for (VAttribute attr : attributes) {
//                 debug("getAttributes: " + attr.getName() );
////                debug("getAttributes: " + attr.getName() + " : " + attr.getValue());
//            }
//            return attributes;
//        } catch (Exception ex) {
//            System.err.println("Can't get attributes!!!!!!!!!");
//            System.err.println(ex.getMessage());
//        }
//        return null;
//    }
    @Override
    public VAttribute[] getAttributes(String[] names) {
        try {
            VAttribute[] attr = node.getAttributes(names);
            debug("getAttributes: " + attr);
            return attr;
        } catch (Exception ex) {
            System.err.println("Can't get attributes!!!!!!!!!");
        }
        return null;
    }

    @Override
    public VAttributeSet getAttributeSet(String[] names) {
        try {
            VAttributeSet attrSet = node.getAttributeSet(names);
            debug("getAttributeSet: " + attrSet);
            return attrSet;
        } catch (Exception ex) {
            System.err.println("Cant get getAttributeSet!");
        }
        return null;
    }

    @Override
    public VAttribute getNonmutableAttribute(String name) {
        try {
            VAttribute attr = node.getNonmutableAttribute(name);
            debug("getNonmutableAttribute: " + attr);
            return attr;
        } catch (Exception ex) {
            System.err.println("Cant get getNonmutableAttribute!");
        }
        return null;
    }

    @Override
    public String getQuery() {
        String query = node.getQuery();
        debug("getQuery: " + query);
        return query;
    }

//    //java.lang.NullPointerException at org.apache.axis2.databinding.typemapping.SimpleTypeMapper.isSimpleType(SimpleTypeMapper.java:202)
//    @Override
//    public String getIconURL(int preferredSize) {
//        String str = null;
//        try {
//            str = node.getIconURL(preferredSize);
//        } catch (Exception ex) {
//            System.err.println("EEEEEEEEERRRRRRRRR: " + ex.getMessage());
//        }
//        
//        return str;
//    }
    @Override
    public String getIconURL() {
        String iconURL = node.getIconURL();
        debug("getIconURL: " + iconURL);
        return iconURL;
    }

    @Override
    public String toString() {
        String str = node.toString();
        debug("toString: " + str);
        return str;
    }

//    @Override
//    public VRL getHelp() {
//        //takes a long time 
//        return node.getHelp();
//    }
//    @Override
//    public INodeMetadata getParent() {
//        //for some reason it goes into recursion
//        try {
//            parentLevel++;
//            debug("parentLevel: " + parentLevel);
//            if (parentLevel == 1) {
//                NodeMetadata parent = new NodeMetadata(node.getParent());
//                debug("getParent: " + parent);
//                return parent;
//            }
//            if(parentLevel > 1){
//                debug("getParent stoping recursion!: ");
//                return null;
//            }
//        } catch (Exception ex) {
//            System.err.println("Cant get getParent!");
//        }
//        return null;
//    }
    @Override
    public URI getParentLocation() {
        try {
            VRL parentLoc = node.getParentLocation();
            debug("getParentLocation: " + parentLoc);
            return parentLoc.toURI();
        } catch (VlURISyntaxException ex) {
            Logger.getLogger(NodeMetadata.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

//    @Override
//    public URI[] getParents() {
//        try {
//            VNode[] parents = node.getParents();
//            for (int i = 0; i < parents.length; i++) {
//                debug("getParents: " + parents[i].getVRL());
//            }
////            NodeMetadata[] parentsMeta = new NodeMetadata[parents.length];
//            URI[] uris = new URI[parents.length];
//            for (int i = 0; i < parents.length; i++) {
////                parentsMeta[i] = new NodeMetadata(parents[i]);
//                uris[i] = parents[i].getVRL().toURI();
//                debug("getParents: " + uris[i]);
//            }
//            return uris;
//        } catch (Exception ex) {
//            System.err.println("Cant get getParent!");
//            ex.printStackTrace();
//        }
//        return null;
//    }

    @Override
    public String getScheme() {
        return node.getScheme();
    }

    @Override
    public String resolvePath(String subPath) {
        return node.resolvePath(subPath);
    }

    @Override
    public VRL resolvePathVRL(String path) {
        try {
            return node.resolvePathVRL(path);
        } catch (Exception ex) {
            System.err.println("Cant resolvePathVRL!");
        }
        return null;
    }

    @Override
    public String getStatus() {
        try {
            return node.getStatus();
        } catch (Exception ex) {
            System.err.println("Can;t get status!!!!!");
        }
        return null;
    }

    @Override
    public boolean sync() {
        try {
            return node.sync();
        } catch (Exception ex) {
            System.err.println("Can't sync!!");
        }
        return false;
    }

    @Override
    public String getType() {
        return node.getType();
    }

    @Override
    public boolean exists() {
        try {
            return node.exists();
        } catch (Exception ex) {
            System.err.println("Dont't if exists!");
        }
        return false;
    }

    private void debug(String msg) {
        System.err.println(this.getClass().getSimpleName() + ": " + msg);
    }
}
