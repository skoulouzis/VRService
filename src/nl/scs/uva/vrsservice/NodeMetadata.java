/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.scs.uva.vrsservice;

import java.net.URI;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.uva.vlet.data.VAttribute;
import nl.uva.vlet.data.VAttributeSet;
import nl.uva.vlet.exception.VlException;
import nl.uva.vlet.exception.VlIOException;
import nl.uva.vlet.exception.VlURISyntaxException;
import nl.uva.vlet.vrl.VRL;
import nl.uva.vlet.vrs.VNode;

/**
 *
 * @author alogo
 */
public class NodeMetadata implements INodeMetadata {

    private final VNode node;
    private final String name;
    private NodeMetadata parent = null;
    private URI uri = null;
    private final String extention;
    private VAttribute[] attributes = null;
    private final long nodeId;
    private final String path;
    private final String hostname;
    private final int port;
    private final String baseName;
    private String charSet = null;
    private final boolean composite;
    private final String[] attrNames;
    private final String query;
    private final String iconURL;
    private final String str;

    public NodeMetadata(VNode node) {
        debug("-----------------New Node "+this.hashCode()+" ----------------");
        this.node = node;
        debug("Node: " + node.hashCode());
        debug("VRL: " + node.getVRL());

        name = node.getName();
        try {
            uri = node.getURI();
        } catch (Exception ex) {
            debug("PROBLEM: " + ex.getMessage());
        }
        extention = node.getExtension();
        try {
            attributes = node.getAttributes();
        } catch (Exception ex) {
            debug("PROBLEM: " + ex.getMessage());
        }
        nodeId = node.getNodeID();
        path = node.getPath();
        hostname = node.getHostname();
        port = node.getPort();
        baseName = node.getBasename();

        try {
            charSet = node.getCharSet();
        } catch (Exception ex) {
            debug("PROBLEM: " + ex.getMessage());
        }
        composite = node.isComposite();
        attrNames = node.getAttributeNames();
        query = node.getQuery();
        iconURL = node.getIconURL();
        str = node.toString();
//        try {
//            parent = new NodeMetadata(node.getParent());
//        } catch (Exception ex) {
//            debug("PROBLEM: " + ex.getMessage());
//        }

        debug("--------------------------------------------");
    }

    @Override
    public String getName() {
        debug("getName: " + name);
        return name;
    }

    @Override
    public URI getURI() {
        try {
            debug("getURI: " + uri);

            return uri;
        } catch (Exception ex) {
            System.err.println("Cant get URI!");

        }

        return null;
    }

    @Override
    public String getExtension() {
        debug("getExtension: " + extention);
        return extention;
    }

    @Override
    public VAttribute getAttribute(String name) {
        for (int i = 0; i < attributes.length; i++) {
            if (attributes[i].getName().equals(name)) {
                debug("getAttribute: " + attributes[i]);
                return attributes[i];
            }
        }
        return null;
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
        debug("getNodeID: " + nodeId);
        return nodeId;
    }

    @Override
    public String getPath() {
        debug("getPath: " + path);
        return path;
    }

    @Override
    public String getHostname() {
        debug("Hostname: " + hostname);
        return hostname;
    }

    @Override
    public int getPort() {
        debug("getPort: " + port);
        return port;
    }

    @Override
    public String getBasename() {
        debug("getBasename: " + baseName);
        return baseName;
    }

    @Override
    public String getMimeType() {
        String type = null;
        try {
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
            debug("getCharSet: " + charSet);
            return charSet;
        } catch (Exception ex) {
            System.err.println("Cant get Char set!");
        }
        return null;
    }

    @Override
    public boolean isComposite() {
        debug("isComposite: " + composite);
        return composite;
    }

    @Override
    public String[] getAttributeNames() {
        debug("getAttributeNames: " + attrNames);
        return attrNames;
    }

//    @Override
//    public VAttribute[] getAttributes() {
//        try {
//            return node.getAttributes();
//        } catch (Exception ex) {
//            System.err.println("Can't get attributes!!!!!!!!!");
//        }
//        return null;
//    }
//    @Override
//    public VAttribute[] getAttributes(String[] names) {
//        try {
//            VAttribute[] attr = node.getAttributes(names);
//            debug("getAttributes: " + attr);
//            return attr;
//        } catch (Exception ex) {
//            System.err.println("Can't get attributes!!!!!!!!!");
//        }
//        return null;
//    }
//
//    @Override
//    public VAttributeSet getAttributeSet(String[] names) {
//        try {
//            VAttributeSet attrSet = node.getAttributeSet(names);
//            debug("getAttributeSet: " + attrSet);
//            return attrSet;
//        } catch (Exception ex) {
//            System.err.println("Cant get getAttributeSet!");
//        }
//        return null;
//    }
//    @Override
//    public VAttribute getNonmutableAttribute(String name) {
//        try {
//            VAttribute attr = node.getNonmutableAttribute(name);
//            debug("getNonmutableAttribute: " + attr);
//            return attr;
//        } catch (Exception ex) {
//            System.err.println("Cant get getNonmutableAttribute!");
//        }
//        return null;
//    }
    @Override
    public String getQuery() {
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
        debug("getIconURL: " + iconURL);
        return iconURL;
    }

    @Override
    public String toString() {
        debug("toString: " + str);
        return str;
    }

//    @Override
//    public VRL getHelp() {
//        //takes a long time 
//        return node.getHelp();
//    }
    @Override
    public INodeMetadata getParent() {
        try {
            //for some reason it goes into recursion 
            debug("getParent: " + parent);
            return parent;
        } catch (Exception ex) {
            System.err.println("Cant get getParent!");
        }
        return null;
    }
//
//    @Override
//    public VRL getParentLocation() {
//        return node.getParentLocation();
//    }
//
//    @Override
//    public INodeMetadata[] getParents() {
//        try {
//            VNode[] parents = node.getParents();
//            NodeMetadata[] parentsMeta = new NodeMetadata[parents.length];
//            for (int i = 0; i < parents.length; i++) {
//                parentsMeta[i] = new NodeMetadata(parents[i]);
//            }
//            return parentsMeta;
//        } catch (Exception ex) {
//             System.err.println("Cant get getParent!");
//        }
//        return null;
//    }
//
//    @Override
//    public String getScheme() {
//        return node.getScheme();
//    }
//
//    @Override
//    public String resolvePath(String subPath) {
//        return node.resolvePath(subPath);
//    }
//
//    @Override
//    public VRL resolvePathVRL(String path) {
//        try {
//            return node.resolvePathVRL(path);
//        } catch (Exception ex) {
//            System.err.println("Cant resolvePathVRL!");
//        }
//        return null;
//    }
//
//    @Override
//    public String getStatus() {
//        try {
//            return node.getStatus();
//        } catch (Exception ex) {
//            System.err.println("Can;t get status!!!!!");
//        }
//        return null;
//    }
//
//    @Override
//    public boolean sync() {
//        try {
//            return node.sync();
//        } catch (Exception ex) {
//            System.err.println("Can't sync!!");
//        }
//        return false;
//    }
//
//    @Override
//    public String getType() {
//        return node.getType();
//    }
//
//    @Override
//    public boolean exists() {
//        try {
//            return node.exists();
//        } catch (Exception ex) {
//            System.err.println("Dont't if exists!");
//        }
//        return false;
//    }

    private void debug(String msg) {
        System.err.println(this.getClass().getSimpleName() + ": " + msg);
    }
}
