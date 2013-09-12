package eu.trojanbug.spring.resource;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.AbstractResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TempFileBufferedResource extends AbstractResource {

    private File bufferFile;
    private String description;

    public TempFileBufferedResource(Resource source) throws IOException {
      this(source,File.createTempFile("SpringResTempBuffer",null));
    }

    public TempFileBufferedResource(Resource source, File backingFile) throws IOException {
        FileUtils.copyInputStreamToFile(source.getInputStream(),backingFile);
        backingFile.deleteOnExit();
    }

    public TempFileBufferedResource(Resource source, File directory, String name) throws IOException {
      this(source, name!=null ? new File(directory,name) : File.createTempFile("SpringResTempBuffer",null,directory));
    }


    public String getDescription() {
        return description!=null ? description : "Temporary file buffered resource, backed by "+bufferFile.getAbsolutePath();
    }

    public InputStream getInputStream() throws IOException {
        return new FileInputStream(bufferFile);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj == this ||
                (obj instanceof TempFileBufferedResource && ((TempFileBufferedResource)obj).bufferFile.equals(bufferFile)));
    }

    /**
     * This implementation returns the hash code based on the underlying String.
     */
    @Override
    public int hashCode() {
        return bufferFile.getAbsolutePath().hashCode();
    }

    @Override
    public String toString() {
        return getDescription();
    }

    /**
     * This implementation always returns {@code true}.
     */
    @Override
    public boolean exists() {
        return bufferFile.exists();
    }

    /**
     * This implementation returns the length of the underlying String after conversion to bytes.
     */
    @Override
    public long contentLength() {
        return bufferFile.length();
    }
}
