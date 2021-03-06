/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.scs.uva.vrsservice;

import nl.uva.vlet.data.VAttribute;
import nl.uva.vlet.exception.VlException;
import nl.uva.vlet.exception.VlURISyntaxException;
import nl.uva.vlet.vfs.VDir;
import nl.uva.vlet.vfs.VFSNode;
import nl.uva.vlet.vfs.VFile;
import nl.uva.vlet.vfs.VUnixFileAttributes;
import nl.uva.vlet.vrl.VRL;
import nl.uva.vlet.vrs.VNode;
import org.apache.axis2.databinding.types.URI;
import org.apache.axis2.databinding.types.URI.MalformedURIException;

/**
 *
 * @author S. Koulouzis
 */
public class NodeMetadata implements INodeMetadata {

    private final VNode node;

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
    public org.apache.axis2.databinding.types.URI getURI() throws MalformedURIException, VlException {
        org.apache.axis2.databinding.types.URI uri;
        uri = new org.apache.axis2.databinding.types.URI(node.getURI().toString());
        debug("getURI: " + uri);
        return uri;
    }

    @Override
    public String getExtension() {
        String extention = node.getExtension();
        debug("getExtension: " + extention);
        return extention;
    }

//    @Override
//    public VAttribute getAttribute(String name) throws VlException {
//        VAttribute attributes = null;
//        attributes = node.getAttribute(name);
//        return attributes;
//    }
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
    public String getMimeType() throws VlException {
        String type = null;
        type = node.getMimeType();
        debug("getMimeType: " + type);
        return type;
    }

    @Override
    public String getCharSet() throws VlException {
        String charSet = node.getCharSet();
        debug("getCharSet: " + charSet);
        return charSet;
    }

    @Override
    public boolean isComposite() {
        boolean composite = node.isComposite();
        debug("isComposite: " + composite);
        return composite;
    }

//    @Override
//    public String[] getAttributeNames() {
//        String[] attrNames = node.getAttributeNames();
//        for (String name : attrNames) {
//            debug("getAttributeNames: " + name);
//        }
//        return attrNames;
//    }
    @Override
    public NodeAttribute[] getAttributes() throws VlException {
        //bug with getDateValue java.lang.reflect.InvocationTargetException
        debug("getAttributes()");
        VAttribute[] attr = node.getAttributes();
        NodeAttribute[] nodeAttr = new NodeAttribute[attr.length];
        for (int i = 0; i < attr.length; i++) {
            nodeAttr[i] = new NodeAttribute(attr[i]);
        }
        return nodeAttr;
    }

//    @Override
//    public NodeAttributes getAttributes(String[] names) throws VlException {
//        allAttributes = new NodeAttributes(node,names);
//        debug("getAttributes: " + attr);
//        return attr;
//    }
//
//    @Override
//    public VAttributeSet getAttributeSet(String[] names) throws VlException {
//        VAttributeSet attrSet = node.getAttributeSet(names);
//        debug("getAttributeSet: " + attrSet);
//        return attrSet;
//    }
//
//    @Override
//    public VAttribute getNonmutableAttribute(String name) throws VlException {
//        VAttribute attr = node.getNonmutableAttribute(name);
//        debug("getNonmutableAttribute: " + attr);
//        return attr;
//    }
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
    public org.apache.axis2.databinding.types.URI getParentLocation() throws VlURISyntaxException, MalformedURIException {
        VRL parentLoc = node.getParentLocation();
        debug("getParentLocation: " + parentLoc);
        return new org.apache.axis2.databinding.types.URI(parentLoc.toURIString());
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
    public org.apache.axis2.databinding.types.URI resolvePathURI(String path) throws VlURISyntaxException, MalformedURIException {
        return new org.apache.axis2.databinding.types.URI(node.resolvePathVRL(path).toURIString());
    }

    @Override
    public String getStatus() throws VlException {
        return node.getStatus();
    }

    @Override
    public boolean sync() throws VlException {
        return node.sync();
    }

    @Override
    public String getType() {
        return node.getType();
    }

    @Override
    public boolean exists() throws VlException {
        return node.exists();
    }

    private void debug(String msg) {
        System.err.println(this.getClass().getSimpleName() + ": " + msg);
    }

    @Override
    public String getBasename(boolean withExtension) throws VlException {
        if (node instanceof VFSNode) {
            return ((VFSNode) node).getBasename(withExtension);
        }
        return null;
    }

    @Override
    public Boolean isHidden() {
        if (node instanceof VFSNode) {
            return ((VFSNode) node).isHidden();
        }
        return null;
    }

    @Override
    public Boolean isSymbolicLink() throws VlException {
        if (node instanceof VFSNode) {
            return ((VFSNode) node).isSymbolicLink();
        }
        return null;
    }

    @Override
    public String getPermissionsString() throws VlException {
        if (node instanceof VFSNode) {
            return ((VFSNode) node).getPermissionsString();
        }
        return null;
    }

    @Override
    public Boolean isRandomAccessable() {
        if (node instanceof VFSNode) {
            return ((VFSNode) node).isRandomAccessable();
        }
        return null;
    }

    @Override
    public Boolean isEditable() throws VlException {
        if (node instanceof VFSNode) {
            return ((VFSNode) node).isEditable();
        }
        return null;
    }

    @Override
    public Boolean isDeletable() throws VlException {
        if (node instanceof VFSNode) {
            return ((VFSNode) node).isDeletable();
        }
        return null;
    }

    @Override
    public Boolean isRenamable() throws VlException {
        if (node instanceof VFSNode) {
            return ((VFSNode) node).isRenamable();
        }
        return null;
    }

    @Override
    public Boolean isFile() {
        if (node instanceof VFSNode) {
            return ((VFSNode) node).isFile();
        }
        return false;
    }

    @Override
    public Boolean isDir() {
        if (node instanceof VFSNode) {
            return ((VFSNode) node).isDir();
        }
        return false;
    }

    @Override
    public Long getModificationTime() throws VlException {
        if (node instanceof VFSNode) {
            return ((VFSNode) node).getModificationTime();
        }
        return null;
    }

    @Override
    public Boolean isReadable() throws VlException {
        if (node instanceof VFSNode) {
            return ((VFSNode) node).isReadable();
        }
        return null;
    }

    @Override
    public Boolean isWritable() throws VlException {
        if (node instanceof VFSNode) {
            return ((VFSNode) node).isWritable();
        }
        return null;
    }

    @Override
    public Integer getMode() throws VlException {
        if (node instanceof VUnixFileAttributes) {
            return ((nl.uva.vlet.vfs.VUnixFileMode) node).getMode();
        }
        return null;
    }

    @Override
    public Long getLength() throws VlException {
        if (node instanceof VFile) {
            return ((VFile) node).getLength();
        }
        return null;
    }

    @Override
    public String[] getResourceTypes() {
        if (node instanceof VDir) {
            return ((VDir) node).getResourceTypes();
        }
        return null;
    }

    @Override
    public Boolean isAccessable() throws VlException {
        if (node instanceof VDir) {
            return ((VDir) node).isAccessable();
        }
        return null;
    }

    @Override
    public URI[] listSorted(boolean typeFirst, boolean ignoreCase) throws VlException, MalformedURIException {
        if (node instanceof VDir) {
            VFSNode[] nodes = ((VDir) node).listSorted(typeFirst, ignoreCase);
            URI[] nodesURI = new URI[nodes.length];

            for (int i = 0; i < nodes.length; i++) {
                nodesURI[i] = new URI(nodes[i].getURI().toString());
            }

            return nodesURI;
        }
        return null;
    }

    @Override
    public Boolean existsFile(String fileName) throws VlException {
        if (node instanceof VDir) {
            return ((VDir) node).existsFile(fileName);
        }
        return null;
    }

    @Override
    public Boolean existsDir(String dirName) throws VlException {
        if (node instanceof VDir) {
            return ((VDir) node).existsDir(dirName);
        }
        return null;
    }

    @Override
    public Boolean hasFile(String fileName) throws VlException {
        if (node instanceof VDir) {
            return ((VDir) node).hasFile(fileName);
        }
        return null;
    }

    @Override
    public Boolean hasDir(String dirName) throws VlException {
        if (node instanceof VDir) {
            return ((VDir) node).hasDir(dirName);
        }
        return null;
    }

    @Override
    public URI[] list() throws VlException, MalformedURIException {
                if (node instanceof VDir) {
            VFSNode[] nodes = ((VDir) node).list();
            URI[] nodesURI = new URI[nodes.length];

            for (int i = 0; i < nodes.length; i++) {
                nodesURI[i] = new URI(nodes[i].getURI().toString());
            }

            return nodesURI;
        }
        return null;
    }
}
