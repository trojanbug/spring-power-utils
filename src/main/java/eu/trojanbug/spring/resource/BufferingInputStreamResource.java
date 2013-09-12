package eu.trojanbug.spring.resource;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.AbstractResource;
import org.springframework.core.io.Resource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class BufferingInputStreamResource extends AbstractResource {

    protected String description;
    protected byte[] content;
    protected final static String DEFAULT_DESCRIPTION = "InputStream based resource";

    public BufferingInputStreamResource(InputStream content) throws IOException {
        this(content,DEFAULT_DESCRIPTION);
    }

    public BufferingInputStreamResource(InputStream content, String description) throws IOException {
      this.description = description;
        try {
          this.content = IOUtils.toByteArray(content);
        } finally {
            IOUtils.closeQuietly(content);
        }
    }

    public BufferingInputStreamResource(Resource content, String description) throws IOException {
        this(content.getInputStream(), description);
    }

    public BufferingInputStreamResource(Resource content) throws IOException {
        this(content.getInputStream());
    }

    public String getDescription() {
        return description;
    }

    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(content);
    }

    /**
     * This implementation compares the underlying buffers.
     */
    @Override
    public boolean equals(Object obj) {
        return (obj == this ||
                (obj instanceof BufferingInputStreamResource && (Arrays.equals(content, ((BufferingInputStreamResource)obj).content))));
    }

    /**
     * This implementation returns the hash code based on the underlying buffer.
     */
    @Override
    public int hashCode() {
        return (content.length *17 + BufferingInputStreamResource.class.hashCode());
    }

    @Override
    public String toString() {
        return description;
    }

    /**
     * This implementation always returns {@code true}.
     */
    @Override
    public boolean exists() {
        return true;
    }

    /**
     * This implementation returns the length of the underlying String after conversion to bytes.
     */
    @Override
    public long contentLength() {
        return content.length;
    }
}
