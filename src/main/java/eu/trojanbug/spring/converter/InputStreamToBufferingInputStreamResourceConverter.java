package eu.trojanbug.spring.converter;

import eu.trojanbug.spring.resource.BufferingInputStreamResource;
import org.springframework.core.convert.converter.Converter;

import java.io.IOException;
import java.io.InputStream;

public class InputStreamToBufferingInputStreamResourceConverter implements Converter<InputStream, BufferingInputStreamResource> {
    public BufferingInputStreamResource convert(InputStream source) {
        try {
            return new BufferingInputStreamResource(source);
        } catch (IOException e) {
            throw new IllegalArgumentException("Could not read provided InputStream: " + source, e);
        }
    }
}