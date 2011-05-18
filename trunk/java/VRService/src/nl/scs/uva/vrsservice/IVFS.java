/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.scs.uva.vrsservice;

import nl.uva.vlet.exception.VlException;
import nl.uva.vlet.exception.VlURISyntaxException;
import nl.uva.vlet.vfs.VFSClient;
import nl.uva.vlet.vfs.VFSTransfer;
import nl.uva.vlet.vrl.VRL;
import org.apache.axis2.databinding.types.URI.MalformedURIException;

/**
 *
 * @author S. Koulouzis
 */
interface IVFS {

    /** Returns VFSNode pointing to the specified location */
    public INodeMetadata openLocation(org.apache.axis2.databinding.types.URI location) throws VlException;

    /**
     * Resolve optional relative location string (URI/VRL) to 
     * current working directory of this VFSClient. 
     * @throws VlURISyntaxException 
     */
    public org.apache.axis2.databinding.types.URI resolve(org.apache.axis2.databinding.types.URI relLoc) throws VlURISyntaxException, MalformedURIException;

    /** Returns remote File or Directory specified by the location*/
    public INodeMetadata getVFSNode(org.apache.axis2.databinding.types.URI uri) throws VlException;

    /** @see #openFile(VRL) */
    public INodeMetadata getFile(org.apache.axis2.databinding.types.URI location) throws VlException;

    /** 
     * Open remote file location and return VFile. 
     * The (remote) file must exist. 
     */
    public INodeMetadata openFile(org.apache.axis2.databinding.types.URI location) throws VlException;

    /** 
     * Resolve (optional relative) location to current workind directory and return VDir. 
     * The resolved directory location must exist. 
     */
    public INodeMetadata getDir(org.apache.axis2.databinding.types.URI loc) throws VlException;

    /** 
     * Open remote directory location and return VDir. 
     * The (remote) directory must exist. 
     */
    public INodeMetadata openDir(org.apache.axis2.databinding.types.URI location) throws VlException;

    /** 
     * Move VFile to remote (VDir) destination.
     * The default implementation will always overwrite existing file(s). 
     * <p> 
     * Calls VFile.moveTo()
     * @return new VFile node if move succeeded.    
     * @throws VlException
     */
    public INodeMetadata move(org.apache.axis2.databinding.types.URI file, org.apache.axis2.databinding.types.URI dest) throws VlException;

    /** 
     * Copy VFile to remote (VDir) destination.
     * The default implementation will always overwrite existing file(s).
     * <p> 
     * Calls VFile.copyTo()
     * @return new VFile node if move succeeded.    
     * @throws VlException
     */
    public INodeMetadata copy(org.apache.axis2.databinding.types.URI vfile, org.apache.axis2.databinding.types.URI parentDir) throws VlException;

//    public  VFSTransfer asyncCopy(org.apache.axis2.databinding.types.URI dir, org.apache.axis2.databinding.types.URI parentDir) throws VlException;
    /** 
     * Move a single directory in the background. 
     * New directory will be created as subdirectory of the parentDir. 
     * 
     * Returns VFSTransfer object which can be used to monitor the result.
     */
//    public  VFSTransfer asyncMove(org.apache.axis2.databinding.types.URI dir, org.apache.axis2.databinding.types.URI parentDir) throws VlException;
    /**
     * Generic rename method. 
     * If the newName starts with a '/' then the full path of the file 
     * or directory will be renamed. 
     * 
     * @param VRL original file or directory
     * @param pathOrName is new name or complete path 
     * @return true if rename was successful  
     * @throws VlException
     */
    public boolean rename(org.apache.axis2.databinding.types.URI uri, String pathOrName) throws VlException;

    /** 
     * Returns true if location is a directory and it exists. 
     * Returns false if location doesn't exists or isn't a directory. 
     * @throws VlException 
     */
    public boolean existsDir(org.apache.axis2.databinding.types.URI location) throws VlException;

    /** 
     * Returns true if location is a File and it exists. 
     * Returns false if location doesn't exists   
     * @throws VlException 
     */
    public boolean existsFile(org.apache.axis2.databinding.types.URI location) throws VlException;

    /** Returns true if path is either a file or an directory */
    public boolean existsPath(org.apache.axis2.databinding.types.URI location) throws VlException;

    /**
     * Tries to create the directory. Parent directory must exist. 
     * For full path creation call: mkdirRecursive().
     */
    public INodeMetadata mkdir(org.apache.axis2.databinding.types.URI loc) throws VlException;

    /**
     * Recursive mkdir. Creates full directory path.  
     * @see mkdirs(VRL,boolean) 
     */
    public INodeMetadata mkdirs(org.apache.axis2.databinding.types.URI loc) throws VlException;

    /** Get local temp directory. On Unix this is "/tmp" */
    public INodeMetadata getTempDir() throws VlException;

    /**
     * Create unique temp directory. 
     * This method creates a hash from the current time, which 
     * is in practise unique
     * @see VFSClient#createFile(VRL, boolean)
     */
    public INodeMetadata createUniqueTempDir() throws VlException;

    /**
     * Specify location which can be used a temp directory instead
     * of the System's default. 
     */
    public boolean setTempDir(org.apache.axis2.databinding.types.URI loc) throws VlURISyntaxException;

    /**
     *  Create new file specified by the VRL. 
     *  If ignoreExisting is false, already existing file won't be created. 
     *  In that case an Exception is thrown. 
     */
    public INodeMetadata createFile(org.apache.axis2.databinding.types.URI filepath, boolean ignoreExisting) throws VlException;

    /**
     * Return grid enabled home of user, might be on a 
     * different host then the current host !
     * Returns VRSContext.getUserHomeLocation(); 
     * <p>  
     * To set the UserHomeLocation, create a new VRSContext and set
     * the host to be used. Then create a new VFSClient with the 
     * new VRSContext. 
     * <pre>
     * VRSContext context=new VRSContext();
     * vrs.setUserHomeLocation([home_url]); 
     * VFSClient vfs=new VFSClient(vrs); 
     * </pre>
     */
    public org.apache.axis2.databinding.types.URI getUserHomeLocation() throws MalformedURIException, VlURISyntaxException;

    /**
     * Set current Working Directory to which relative VRLs will
     * be resolved.  
     * 
     * @param dir
     */
    public void setWorkingDir(org.apache.axis2.databinding.types.URI dir) throws VlURISyntaxException;

    /** 
     * Returns current working directory for relative URLs
     * as specified in the use VRSContext. 
     */
    public org.apache.axis2.databinding.types.URI getWorkingDir() throws VlURISyntaxException, MalformedURIException;

    public INodeMetadata[] list(org.apache.axis2.databinding.types.URI path) throws VlException;

    public INodeMetadata[] list(org.apache.axis2.databinding.types.URI path, String regexpFilter) throws VlException;

    /**
     * Create new VFile object. 
     * The location doesn't have to exist, only the actual VFile object is created.  
     */
    public INodeMetadata newFile(org.apache.axis2.databinding.types.URI location) throws VlException;

    /**
     * Create new VDir object. 
     * The location doesn't have to exist, only the actual VDir object is created.  
     */
    public INodeMetadata newDir(org.apache.axis2.databinding.types.URI location) throws VlException;
}
