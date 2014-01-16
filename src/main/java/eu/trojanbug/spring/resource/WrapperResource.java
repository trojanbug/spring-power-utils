package eu.trojanbug.spring.resource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import org.springframework.core.io.*;

public class WrapperResource extends AbstractResource
{

    public WrapperResource(Resource toBeWrapped)
        throws IOException
    {
        wrappedResource = toBeWrapped;
    }

    public WrapperResource(URL content)
        throws IOException
    {
        wrappedResource = new UrlResource(content);
    }

    public WrapperResource(URI content)
        throws IOException
    {
        wrappedResource = new UrlResource(content);
    }

    public WrapperResource(InputStream content)
        throws IOException
    {
        wrappedResource = new BufferingInputStreamResource(content);
    }

    public InputStream getInputStream()
        throws IOException
    {
        return wrappedResource.getInputStream();
    }

    public String getDescription()
    {
        return wrappedResource.getDescription();
    }

    protected Resource wrappedResource;
}
