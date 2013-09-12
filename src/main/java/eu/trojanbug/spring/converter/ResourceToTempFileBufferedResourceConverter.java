package eu.trojanbug.spring.converter;

import eu.trojanbug.spring.resource.BufferingInputStreamResource;
import eu.trojanbug.spring.resource.TempFileBufferedResource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.io.Resource;

import java.io.IOException;

public class ResourceToTempFileBufferedResourceConverter implements Converter<Resource, TempFileBufferedResource> {
    public TempFileBufferedResource convert(Resource source) {
        try {
            return new TempFileBufferedResource(source);
        } catch (IOException e) {
            throw new IllegalArgumentException("Could not get read from provided resource: " + source, e);
        }
    }
}
