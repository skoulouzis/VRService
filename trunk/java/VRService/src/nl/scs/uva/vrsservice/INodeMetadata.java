/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.scs.uva.vrsservice;

import nl.uva.vlet.exception.VlException;
import nl.uva.vlet.exception.VlIOException;
import nl.uva.vlet.exception.VlURISyntaxException;
import org.apache.axis2.databinding.types.URI;
import org.apache.axis2.databinding.types.URI.MalformedURIException;

/**
 *
 * @author S. Koulouzis
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
    public NodeAttribute[] getAttributes() throws VlException;

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

    public String getBasename(boolean withExtension) throws VlException;

    /**
     * Optional method for filesystems who support hidden files. 
     * Note that the implementation of hidden files on filesystems
     * might differ!
     * Default implemententation is to return true for 'dot' files. 
     */
    public Boolean isHidden();

    /**
     * Optional method for filesystems who support symbolic links
     * or File Aliases (LFC). 
     * Default this method return false. 
     * Note that implementations of links on filesystems might differ!
     * @throws VlException 
     */
    public Boolean isSymbolicLink() throws VlException;

    /**
     * Returns Symbolic LinkTarget as VRL 
     * (if this resource is an symbolic link) NULL otherwise.  
     *  
     * Filesystem implementations might differ how they handle symbolic links.
     *   
     * @throws VlException 
     */
//    public URI getSymbolicLinkTargetURI() throws VlException, MalformedURIException;

    /**
     * Returns Permissions in Unix like String.  
     * For example "-rwxr-xr-x" for a linux file.
     * This method checks whether this resource implements VUnixFileMode 
     * and used the Unix File Mode to generate the permissions string. 
     * @see nl.uva.vlet.vfs.VUnixFileMode
     */
    public String getPermissionsString() throws VlException;

    /**
     * RandomAccessable methods:
     */
    public Boolean isRandomAccessable();

    //=========================================================================
    // VEditable interface
    //=========================================================================
    /**
     * Default File implementions has editable attributes.
     * Default return value is true; 
     * This differs from isWritable as file persmissions are 'editable' 
     * even if the file is not writable. 
     */
    public Boolean isEditable() throws VlException;

    public Boolean isDeletable() throws VlException;

    //=========================================================================
    // VRenamable interface
    //=========================================================================
    public Boolean isRenamable() throws VlException;

//    // ========================
//    // ACL interface : Under Construction  
//    // =========================
//
//    public void setACL(VAttribute[][] acl) throws VlException
//    {
//        if (this instanceof VUnixFileMode)
//        {
//             this.setUXACL(acl); 
//        }
//        else
//        {
//            throw new nl.uva.vlet.exception.ResourceTypeMismatchException("Resource doensn't support ACL:"+this); 
//        }
//    }
//    
//    /**
//     * Universal Access Control List to support multiple permission schemes.
//     * The returned matrix ACL[N][M] is a list of N entities specifying M permissions
//     * for Entity N. 
//     * For more details {@linkplain VACL}.  
//     * @see VACL 
//     */ 
//    public VAttribute[][] getACL() throws VlException;
//
//     * Universal Access Control List to support multiple permission schemes.
//     * The returned matrix ACL[N][M] is a list of N entities specifying M permissions
//     * for Entity N. 
//     * For more details {@linkplain VACL}.  
//     * @see VACL 
//     */ 
//    public VAttribute[][] getACL() throws VlException;
//    public VAttribute[][] getUXACL() throws VlException;
//    
//    /**
//     * Converts the "user,group,other" permissions attribute list 
//     * to a Unix file mode and changes this if this file supported
//     * Unix style permission rights.
//     *   
//     * @see VFS.convertACL2FileMode
//     * 
//     * @throws VlException
//     */
//    public void setUXACL(VAttribute[][] acl)throws VlException;
//
//    /** 
//     * Returns all possible ACL entities (users,groups, etc); 
//     * @throws VlIOException 
//     */ 
//    public VAttribute[] getACLEntities() throws VlIOException;
//
//    /**
//     *  Create a new ACL Record for the given ACL Entry, that is, a new row
//     *  in the ACL[][] matrix returned in getACL(). 
//     *  The nr of- and types in this row must match. 
//     * @param writeThrough 
//     * 
//     * @return
//     * @throws NotImplementedException 
//     */
//    public VAttribute[] createACLRecord(VAttribute entity, boolean writeThrough) throws VlException;
    /** Delete entry in the ACL list or set permissions to none */
//    public boolean deleteACLEntity(VAttribute entity) throws VlException;
    /**
     * Returns true if the node is a file.
     * @see VFile 
     */
    public abstract Boolean isFile();

    /**
     * Returns true if the node is a Directory 
     * @see VDir
     */
    public abstract Boolean isDir();

    /**
     * Return time of last modification in milli seconds after 'epoch'
     * epoch = (1-jan-1970 GMT). 
     * @throws VlException
     */
    public abstract Long getModificationTime() throws VlException;

    /**
     * Returns whether the object is readable using current user credentials.
     * <br>
     * For a Directory isReadable means it must have r-x permissions !
     * 
     * @throws VlException
     * @see exists
     * @see isWritable
     */
    public abstract Boolean isReadable() throws VlException;

    /**
     * Returns whether the object is writable using current user credentials.
     * Note that some implementations make a difference between 
     * 'deletable' 'appendable' and 'can create directories' (GridFTP). 
     *
     * @see exists
     * @see isReadable
     */
    public abstract Boolean isWritable() throws VlException;
    
    public Integer getMode() throws VlException;
    
    
    // ========================================================================
    // Extra VFile Abstract Interface Methods 
    // ========================================================================

    /** 
     * Returns size (length) of files. Directories may return storage size
     * needed to store the directory entries. 
     * @return size of file or directory. Returns -1 if size is unknown.   
     */
    public abstract Long getLength() throws VlException;
    
       /**
     * Returns allowed child types for VDir.<br>
     * <br>
     * The default types for VDir are 'File' and 'Dir' type<br>
     */
    public String[] getResourceTypes();

    /**
     * For unix fileystem this means the 'x' bit should be enabled. 
     */
    public Boolean isAccessable() throws VlException;

    /**
     * List the chidren and sort them. 
     * @param typeFirst  if true return directories first, then files. 
     * @param ignoreCase ignore case when sorting 
     * @return Sorted VFSNode[] array
     * @throws VlException 
     */
    public URI[] listSorted(boolean typeFirst, boolean ignoreCase) throws VlException, MalformedURIException;

    /** 
     * Returns true whether (child) filename exists and is a VFile.
     * Parameter fileName can be an absolute path or relative path starting from this 
     * directory.   
     */
    public Boolean existsFile(String fileName) throws VlException;

    /**
     * Returns true whether (child) directory exists and is a VDir.
     * Parameter dirName can be an absolute path or relative path starting from this 
     * directory.   
     */
    public Boolean existsDir(String dirName) throws VlException;

    /** Alias for existsFile 
     *  @see VDir#existsFile(String) */
    public Boolean hasFile(String fileName) throws VlException;

    /** Alias for existsDir 
     * @see VDir#existsDir(String) */
    public Boolean hasDir(String dirName) throws VlException;

    // ========================================================================
    // Abstract Interface Methods 
    // ========================================================================
    /**
     * Return listed contents of Directory.
     * <p> 
     * For large Directories and for optimized filtering it is recommended that
     * the method {@link #list(NodeFilter, int, int, IntegerHolder)} is also
     * overriden. 
     * 
     * @return array of VFSNodes  
     * @throws VlException
     */
    public abstract URI[] list() throws VlException, MalformedURIException;

}
