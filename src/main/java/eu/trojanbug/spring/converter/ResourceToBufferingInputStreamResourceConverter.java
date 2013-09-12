package eu.trojanbug.spring.converter;

import eu.trojanbug.spring.resource.BufferingInputStreamResource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.io.Resource;

import java.io.IOException;

public class ResourceToBufferingInputStreamResourceConverter implements Converter<Resource, BufferingInputStreamResource> {
    public BufferingInputStreamResource convert(Resource source) {
        try {
            return new BufferingInputStreamResource(source);
        } catch (IOException e) {
            throw new IllegalArgumentException("Could not get InputStream from provided resource: " + source, e);
        }
    }
}
