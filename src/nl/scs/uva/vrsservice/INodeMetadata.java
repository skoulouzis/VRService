/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.scs.uva.vrsservice;

import nl.uva.vlet.data.VAttribute;
import nl.uva.vlet.data.VAttributeSet;
import nl.uva.vlet.exception.VlException;
import nl.uva.vlet.exception.VlIOException;
import nl.uva.vlet.exception.VlURISyntaxException;
import nl.uva.vlet.vrl.VRL;
import org.apache.axis2.databinding.types.URI.MalformedURIException;

/**
 *
 * @author alogo
 */
interface INodeMetadata {

    public String getName();

    public org.apache.axis2.databinding.types.URI getURI() throws MalformedURIException, VlException;

    public String getExtension();

    /** 
     * This is the single method a Node has to implement so that attributes can be fetched.  
     * subclasses can override this method and do a super.getAttribute first to
     * check whether the superclass provides an attribute name. 
     *  
     * @throws VlException */
//    public VAttribute getAttribute(String name) throws VlException;

//    public VRL getVRL();
//
//    public URL getURL();
//
    public long getNodeID();

    /** Returns logical path of this resource */
    public String getPath();

    /** Returns Hostname */
    public String getHostname();

    /** Returns Port. If the value <=0 then the default port must be used. */
    public int getPort();

    /** Returns basename part of the path of a node. */
    public String getBasename();

    /** 
     * Returns Mime Type based upon file filename/extension. 
     * For a more robust method, use MimeTypes.getMagicMimeType().
     * 
     * @throws VlException 
     *  
     * @see MimeTypes.getMagicMimeType(byte[]) 
     * @see MimeTypes.getMimeType(String) 
     */
    public String getMimeType() throws VlException;

    /**
     * Default charset for text resources 
     * @throws VlIOException 
     * @throws VlException 
     */
    public String getCharSet() throws VlException;

    /**
     * Highlevel method to check whether the 
     * node has the VComposite interface 
     */
    public boolean isComposite();

    /** Get the names of the attributes this resource has */
//    public String[] getAttributeNames() throws VlException;

    /** 
     * Get all attributes defined by attributeNames 
     * @throws VlException 
     */
    public NodeAttributes getAttributes() throws VlException;
    /** 
     * Get all attributes defined by <code>names</code>.<br>
     * Elements in the <code>names</code> array may be null! 
     * It means do not fetch the attribute.
     * This is to speed up fetching of indexed attributes.
     * <br>
     * <b>Developpers note</b>:<br>
     * Subclasses are encouraged to overide this method to 
     * speed up fetching multiple attributes as this method
     * does a getAttribute call per attribute. 
     * @throws VlException 
     */
//    public VAttribute[] getAttributes(String names[]) throws VlException;

    /**
     * Same as getAttributes(), but return the attributes in an 
     * (Ordened) Attribute set. 
     * 
     * @param names
     * @return
     * @throws VlException 
     */
//    public VAttributeSet getAttributeSet(String names[]) throws VlException;

    /** 
     * Get Non-mutable attribute. This is an attribute which can be derived from the 
     * location or doesn't change during the lifetime of the Object because it 
     * is implicit bound to the object class.   
     * Even if the object doesn't exist the attribute can be determined, for 
     * example the Resource Type of a VFile which doesn't change during 
     * the lifetime of the (VFile) Object as this always must be "File" !   
     */
//    public VAttribute getNonmutableAttribute(String name) throws VlException;

    /** Return Query part of VRL */
    public String getQuery();

//    /**
//     * Returns optional icon URL given the preferred size. 
//     * Default implementation is to call getIconURL(). 
//     * This method allows resources to return different icons
//     * for different sizes. 
//     * The actual displayed size in the vbrowser may differ
//     * from the given size and the preferredSize should be regarded as an indication. 
//     * It is recommended to provide optimized icons for sizes less
//     * than or equal to 16. 
//     */
//    public String getIconURL(int preferredSize);
    /** Returns optional icon url */
    public String getIconURL();

    //        /**
//     * Returns simple text file or complete HTML page. Method should point to
//     * installed documentation.
//     * Default is to return help about this type. 
//     */
//    public VRL getHelp();
    /**
     * Return this node's location as String representation.<br>
     * Note that special characters are not encoded.
     */
    public String toString();

    /** 
     * Get Parent Node (if any).<br>
     * Default implementation is to open the location provided
     * by getParentLocation(). Override that method to provide
     * the parent location of this node.  
     * Overide this method to provide a more eficient way 
     * to return a VNode that is the (logical) parent of this. 
     * 
     * @see #getParents()
     * @see #getParentLocation()
     * @return Parent VNode or null. 
     * @throws VlException
     */
//    public INodeMetadata getParent();
//
//    /**
//     * Returns logical parent location of this node. 
//     * By default this method returns getVRL().getParent(); 
//     * If an implementation has another 'logical' parent then just
//     * the dirname of the current location, override this method. 
//     */
    public org.apache.axis2.databinding.types.URI getParentLocation() throws VlURISyntaxException, MalformedURIException;

    /** 
     * Get Parents if the Node is part of a Graph.
     * <br>
     * Returns one parent if Node is part of a Tree or null if
     * Node has no parents. 
     * @throws VlException
     */
//    public URI[] getParents();
    public String getScheme();

    /** 
     * Resolve relative or absolute path against this resource. 
     * Uses VRL.resolvePaths(this.getPath(),subPath) as default 
     * implementation.  
     */
    public String resolvePath(String subPath);

    /** Resolve path against this VRL and return resolved VRL */
    public org.apache.axis2.databinding.types.URI resolvePathURI(String path) throws MalformedURIException, VlURISyntaxException;

    /** 
     * Status String for nodes which implemented Status. 
     * Returns NULL if not supported. 
     * This method is exposed in the toplevel VNode interface even if not supported.  
     * @return
     * @throws VlException 
     */
    public String getStatus() throws VlException;

    /** 
     * Synchronized cached attributes and/or refresh (optional) cached attributes
     * from remote resource. 
     * This is an import method in the case that an resource caches resource attributes, like
     * file attributes. 
     * @since VLET 1.2 
     * @return - false : not applicable/not implemented for this resource.<br>
     *         - true : synchronize/refresh is implemented and was successful.  
     * @throws VlException when resource synchronisation wasn't successful   
     */
    public boolean sync() throws VlException;
//
//    // ========================================================================
//    // Abstract Interface 
//    // ========================================================================

    /** Returns resource type, if it has one */
    public abstract String getType();

    /** Whether this node (still) exists 
     * @throws VlException */
    public abstract boolean exists() throws VlException;
}
