package eu.trojanbug.spring.resource;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.AbstractResource;
import org.springframework.core.io.Resource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

public class PropertiesResource extends AbstractResource {

    private static final String DEFAULT_DESCRIPTION = "Properties object based resource";
    private byte[] propertiesSerialized;
    private String description;

    public PropertiesResource(Properties properties) {
        this(properties, DEFAULT_DESCRIPTION);
    }

    public PropertiesResource(Properties properties, String description) {
        this.description = description!=null ? description : DEFAULT_DESCRIPTION;
        setProperties(properties);
    }

    public PropertiesResource(Resource propertiesLocation, String description) throws IOException {
        this.description = description!=null ? description : DEFAULT_DESCRIPTION;
        InputStream sourceInputStream = null;
        try {
            sourceInputStream = propertiesLocation.getInputStream();
            propertiesSerialized = IOUtils.toByteArray(sourceInputStream);
        } finally {
            IOUtils.closeQuietly(sourceInputStream);
        }

    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(propertiesSerialized);
    }

    public final void setProperties(Properties properties) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            properties.store(baos,"");
        } catch (IOException e) {
            // should not happen!
        }
        propertiesSerialized = baos.toByteArray();
    }

    /**
     * This implementation always returns {@code true}.
     */
    @Override
    public boolean exists() {
        return true;
    }

    /**
     * This implementation returns the length of the underlying byte array.
     */
    @Override
    public long contentLength() {
        return this.propertiesSerialized.length;
    }

    /**
     * This implementation compares the underlying byte array.
     * @see java.util.Arrays#equals(byte[], byte[])
     */
    @Override
    public boolean equals(Object obj) {
        return (obj == this ||
                (obj instanceof PropertiesResource && Arrays.equals(((PropertiesResource) obj).propertiesSerialized, this.propertiesSerialized)));
    }

    /**
     * This implementation returns the hash code based on the
     * underlying byte array.
     */
    @Override
    public int hashCode() {
        return (Properties.class.hashCode() * 19 * this.propertiesSerialized.length);
    }

}
