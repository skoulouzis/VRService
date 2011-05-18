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
import nl.uva.vlet.vfs.VFile;
import nl.uva.vlet.vrl.VRL;
import nl.uva.vlet.vrs.VRSContext;
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
    public INodeMetadata openLocation(org.apache.axis2.databinding.types.URI location) throws VlException {
        VFSNode node = client.openLocation(location.toString());
        return new NodeMetadata(node);
    }

    @Override
    public INodeMetadata getFile(org.apache.axis2.databinding.types.URI locStr) throws VlException {
        VFile node = client.getFile(locStr.toString());
        return new NodeMetadata(node);
    }

    @Override
    public org.apache.axis2.databinding.types.URI resolve(org.apache.axis2.databinding.types.URI relLoc) throws VlURISyntaxException, MalformedURIException {
        VRL res = client.resolve(relLoc.toString());
        return new org.apache.axis2.databinding.types.URI(res.toURIString());
    }

    @Override
    public INodeMetadata getVFSNode(org.apache.axis2.databinding.types.URI uri) throws VlException {
        VFSNode node = client.getVFSNode(uri.toString());
        return new NodeMetadata(node);
    }

    @Override
    public INodeMetadata openFile(org.apache.axis2.databinding.types.URI location) throws VlException {
        VFile file = client.openFile(new VRL(location.toString()));
        return new NodeMetadata(file);
    }

    @Override
    public INodeMetadata getDir(org.apache.axis2.databinding.types.URI loc) throws VlException {
        VDir dir = client.getDir(loc.toString());
        return new NodeMetadata(dir);
    }

    @Override
    public INodeMetadata openDir(org.apache.axis2.databinding.types.URI location) throws VlException {
        VDir dir = client.openDir(new VRL(location.toString()));
        return new NodeMetadata(dir);
    }

    @Override
    public INodeMetadata move(org.apache.axis2.databinding.types.URI source, org.apache.axis2.databinding.types.URI dest) throws VlException {
        VFSNode sourceNode = client.openLocation(source.toString());
        VFSNode destNode = client.openLocation(dest.toString());

        if (sourceNode.isDir() && destNode.isDir()) {
            return new NodeMetadata(client.move(((VDir) sourceNode), ((VDir) destNode)));

        }
        if (sourceNode.isFile() && destNode.isFile()) {
            return new NodeMetadata(client.move(((VFile) sourceNode), ((VFile) destNode)));
        }

        if (sourceNode.isFile() && destNode.isDir()) {
            return new NodeMetadata(client.move(((VFile) sourceNode), ((VDir) destNode)));
        }
        return null;
    }

    @Override
    public INodeMetadata copy(org.apache.axis2.databinding.types.URI source, org.apache.axis2.databinding.types.URI dest) throws VlException {
        VFSNode sourceNode = client.openLocation(source.toString());
        VFSNode destNode = client.openLocation(dest.toString());
        if (sourceNode.isDir() && destNode.isDir()) {
            return new NodeMetadata(client.copy(((VDir) sourceNode), ((VDir) destNode)));

        }
        if (sourceNode.isFile() && destNode.isFile()) {
            client.copy(((VFile) sourceNode), ((VFile) destNode));
            return new NodeMetadata(destNode);
        }

        if (sourceNode.isFile() && destNode.isDir()) {
            return new NodeMetadata(client.copy(((VFile) sourceNode), ((VDir) destNode)));
        }
        return null;
    }

//    @Override
//    public VFSTransfer asyncCopy(org.apache.axis2.databinding.types.URI dir, org.apache.axis2.databinding.types.URI parentDir) throws VlException {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//    @Override
//    public VFSTransfer asyncMove(org.apache.axis2.databinding.types.URI dir, org.apache.axis2.databinding.types.URI parentDir) throws VlException {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
    @Override
    public boolean rename(org.apache.axis2.databinding.types.URI uri, String pathOrName) throws VlException {
        return client.rename(new VRL(uri.toString()), pathOrName);
    }

    @Override
    public boolean existsDir(org.apache.axis2.databinding.types.URI location) throws VlException {
        return client.existsDir(location.toString());
    }

    @Override
    public boolean existsFile(org.apache.axis2.databinding.types.URI location) throws VlException {
        return client.existsFile(location.toString());
    }

    @Override
    public boolean existsPath(org.apache.axis2.databinding.types.URI location) throws VlException {
        return client.existsPath(new VRL(location.toString()));
    }

    @Override
    public INodeMetadata mkdir(org.apache.axis2.databinding.types.URI loc) throws VlException {
        VDir dir = client.mkdir(loc.toString());
        return new NodeMetadata(dir);
    }

    @Override
    public INodeMetadata mkdirs(org.apache.axis2.databinding.types.URI loc) throws VlException {
        VDir dir = client.mkdirs(new VRL(loc.toString()));
        return new NodeMetadata(dir);
    }

    @Override
    public INodeMetadata getTempDir() throws VlException {
        return new NodeMetadata(client.getTempDir());
    }

    @Override
    public INodeMetadata createUniqueTempDir() throws VlException {
        return new NodeMetadata(client.createUniqueTempDir());
    }

    @Override
    public boolean setTempDir(org.apache.axis2.databinding.types.URI loc) throws VlURISyntaxException {
        return client.setTempDir(new VRL(loc.toString()));
    }

    @Override
    public INodeMetadata createFile(org.apache.axis2.databinding.types.URI filepath, boolean ignoreExisting) throws VlException {
        VFile file = client.createFile(new VRL(filepath.toString()), ignoreExisting);
        return new NodeMetadata(file);
    }

    @Override
    public org.apache.axis2.databinding.types.URI getUserHomeLocation() throws MalformedURIException, VlURISyntaxException {
        VRL homeLoc = client.getUserHomeLocation();
        return new org.apache.axis2.databinding.types.URI(homeLoc.toURIString());
    }

    @Override
    public void setWorkingDir(org.apache.axis2.databinding.types.URI dir) throws VlURISyntaxException {
        client.setWorkingDir(new VRL(dir.toString()));
    }

    @Override
    public org.apache.axis2.databinding.types.URI getWorkingDir() throws VlURISyntaxException, MalformedURIException {
        VRL vrl = client.getWorkingDir();
        return new org.apache.axis2.databinding.types.URI(vrl.toURIString());
    }

    @Override
    public INodeMetadata[] list(org.apache.axis2.databinding.types.URI path) throws VlException {
        VFSNode[] nodes = client.list(new VRL(path.toString()));
        NodeMetadata[] nodesMeta = new NodeMetadata[nodes.length];
        for (int i = 0; i < nodes.length; i++) {
            nodesMeta[i] = new NodeMetadata(nodes[i]);
        }
        return nodesMeta;
    }

    @Override
    public INodeMetadata[] list(org.apache.axis2.databinding.types.URI path, String regexpFilter) throws VlException {
        VFSNode[] nodes = client.list(new VRL(path.toString()), regexpFilter);
        NodeMetadata[] nodesMeta = new NodeMetadata[nodes.length];
        for (int i = 0; i < nodes.length; i++) {
            nodesMeta[i] = new NodeMetadata(nodes[i]);
        }
        return nodesMeta;
    }

    @Override
    public INodeMetadata newFile(org.apache.axis2.databinding.types.URI location) throws VlException {
        VFile file = client.newFile(location.toString());
        return new NodeMetadata(file);
    }

    @Override
    public INodeMetadata newDir(org.apache.axis2.databinding.types.URI location) throws VlException {
        VDir dir = client.newDir(location.toString());
        return new NodeMetadata(dir);
    }
}
