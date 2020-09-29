package deltix.qsrv.dtb.fs.common;

import deltix.qsrv.dtb.fs.pub.AbstractFileSystem;
import deltix.qsrv.dtb.fs.pub.AbstractPath;
import deltix.util.lang.Wrapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @param <T> Type of wrapping class. We need it to enforce correct wrapping everywhere.
 */
public abstract class DelegatingAbstractPath<T extends AbstractPath> implements AbstractPath, Wrapper<AbstractPath> {
    protected final AbstractPath delegate;

    protected DelegatingAbstractPath(AbstractPath delegate) {
        this.delegate = delegate;
    }

    protected abstract T wrap(AbstractPath path);

    @Override
    public AbstractPath getNestedInstance() {
        return delegate;
    }

    @Override
    public AbstractFileSystem getFileSystem() {
        throw new IllegalStateException("This method must be overridden in child class an should return wrapping file system");
    }

    @Override
    public String getPathString() {
        return delegate.getPathString();
    }

    @Override
    public String getName() {
        return delegate.getName();
    }

    @Override
    public String[] listFolder() throws IOException {
        return delegate.listFolder();
    }

    @Override
    public T append(String name) {
        return wrap(delegate.append(name));
    }

    @Override
    public T getParentPath() {
        return wrap(delegate.getParentPath());
    }

    @Override
    public InputStream openInput(long offset) throws IOException {
        return delegate.openInput(offset);
    }

    @Override
    public OutputStream openOutput(long size) throws IOException {
        return delegate.openOutput(size);
    }

    @Override
    public long length() {
        return delegate.length();
    }

    @Override
    public boolean isFile() {
        return delegate.isFile();
    }

    @Override
    public boolean isFolder() {
        return delegate.isFolder();
    }

    @Override
    public boolean exists() {
        return delegate.exists();
    }

    @Override
    public void makeFolder() throws IOException {
        delegate.makeFolder();
    }

    @Override
    public void makeFolderRecursive() throws IOException {
        delegate.makeFolderRecursive();
    }

    @Override
    public void moveTo(AbstractPath newPath) throws IOException {
        delegate.moveTo(newPath);
    }

    @Override
    public T renameTo(String newName) throws IOException {
        return wrap(delegate.renameTo(newName));
    }

    @Override
    public void deleteExisting() throws IOException {
        delegate.deleteExisting();
    }

    @Override
    public void deleteIfExists() throws IOException {
        delegate.deleteIfExists();
    }

    @Override
    public String toString() {
        return delegate.toString();
    }
}
