/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.scs.uva.vrsservice;

import nl.uva.vlet.Global;
import nl.uva.vlet.GlobalConfig;
import nl.uva.vlet.exception.VlException;
import nl.uva.vlet.exception.VlURISyntaxException;
import nl.uva.vlet.vfs.VDir;
import nl.uva.vlet.vfs.VFSClient;
import nl.uva.vlet.vfs.VFSNode;
import nl.uva.vlet.vfs.VFSTransfer;
import nl.uva.vlet.vfs.VFile;
import nl.uva.vlet.vrl.VRL;
import nl.uva.vlet.vrs.VRSContext;
import org.apache.axis2.databinding.types.URI;
import org.apache.axis2.databinding.types.URI.MalformedURIException;

/**
 *
 * @author S. Koulouzis
 */
public class VFService implements IVFS {

    private VFSClient client;
    private static final boolean isInService = true;
    private final VRSContext vrsContext;

    public VFService() {

        GlobalConfig.setIsService(isInService);
        GlobalConfig.setInitURLStreamFactory(!isInService);
        Global.init();

        vrsContext = VRSContext.getDefault();
        this.client = new VFSClient(vrsContext);
    }

    private void debug(String msg) {
        System.err.println(this.getClass().getSimpleName() + ": " + msg);
    }

    @Override
    public IVFSNodeMetadata openLocation(org.apache.axis2.databinding.types.URI location) throws VlException {
        VFSNode node = client.openLocation(location.toString());
        return new VFSNodeMetadata(node);
    }

    @Override
    public IVFileMetadata getFile(org.apache.axis2.databinding.types.URI locStr) throws VlException {
        VFile node = client.getFile(locStr.toString());
        return new VFileMetadata(node);
    }
    

    @Override
    public URI resolve(URI relLoc) throws VlURISyntaxException, MalformedURIException {
        VRL res = client.resolve(relLoc.toString());
        return new URI(res.toURIString());
    }

    @Override
    public IVFSNodeMetadata getVFSNode(URI uri) throws VlException {
        VFSNode node = client.getVFSNode(uri.toString());
        return new VFSNodeMetadata(node);
    }

    @Override
    public IVFileMetadata openFile(URI location) throws VlException {
        VFile file = client.openFile(new VRL(location.toString()));
        return new VFileMetadata(file);
    }

    @Override
    public IVDirMetadata getDir(URI loc) throws VlException {
        VDir dir = client.getDir(loc.toString());
        return new VDirMetadata(dir);
    }

    @Override
    public IVDirMetadata openDir(URI location) throws VlException {
        VDir dir = client.openDir(new VRL(location.toString()));
        return new VDirMetadata(dir);
    }

    @Override
    public IVFileMetadata move(URI file, URI dest) throws VlException {                
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public IVFSNodeMetadata copy(URI vfile, URI parentDir) throws VlException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public VFSTransfer asyncCopy(URI dir, URI parentDir) throws VlException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public VFSTransfer asyncMove(URI dir, URI parentDir) throws VlException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean rename(URI uri, String pathOrName) throws VlException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean existsDir(URI location) throws VlException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean existsFile(URI location) throws VlException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean existsPath(URI location) throws VlException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public IVDirMetadata mkdir(URI loc) throws VlException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public IVDirMetadata mkdirs(URI loc) throws VlException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public IVDirMetadata getTempDir() throws VlException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public IVDirMetadata createUniqueTempDir() throws VlException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean setTempDir(URI loc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public IVFileMetadata createFile(URI filepath, boolean ignoreExisting) throws VlException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public URI getUserHomeLocation() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setWorkingDir(IVDirMetadata dir) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public URI getWorkingDir() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setWorkingDir(URI uri) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public IVFSNodeMetadata[] list(URI path) throws VlException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public IVFSNodeMetadata[] list(URI path, String regexpFilter) throws VlException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public IVFileMetadata newFile(URI location) throws VlException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public IVDirMetadata newDir(URI location) throws VlException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
