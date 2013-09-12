package eu.trojanbug.spring.resource;

import org.springframework.core.io.AbstractResource;

import java.io.*;
import java.nio.charset.Charset;

public class StringResource extends AbstractResource {

    protected String description;
    protected String content;
    protected Charset charset;
    protected final static String DEFAULT_DESCRIPTION = "String based resource";

    public StringResource(String content) {
      this(content,DEFAULT_DESCRIPTION);
    }

    public StringResource(String content, String description) {
      this(content, description, Charset.defaultCharset());
    }

    public StringResource(String content, String description, Charset charset) {
       this.content = content;
       this.description = description!=null ? description : DEFAULT_DESCRIPTION;
       this.charset = charset!=null ? charset : Charset.defaultCharset();
    }

    public StringResource(String content, String description, String charset) {
        this(content,description,Charset.forName(charset));
    }

    public StringResource(String content, Charset charset) {
      this(content,DEFAULT_DESCRIPTION,charset);
    }

    public String getDescription() {
        return description;
    }

    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(content.getBytes(charset));
    }

    /**
     * This implementation compares the underlying Strings.
     */
    @Override
    public boolean equals(Object obj) {
        return (obj == this ||
                (obj instanceof StringResource && ((StringResource)obj).content.equals(content)));
    }

    /**
     * This implementation returns the hash code based on the underlying String.
     */
    @Override
    public int hashCode() {
        return (content.hashCode() *17 + StringResource.class.hashCode());
    }

    @Override
    public String toString() {
        return content;
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
        return content.getBytes(charset).length;
    }
}
