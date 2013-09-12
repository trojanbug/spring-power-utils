package eu.trojanbug.spring.converter;

import eu.trojanbug.spring.resource.PropertiesResource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.io.Resource;

import java.util.Properties;

public class PropertiesToResourceConverter implements Converter<Properties, Resource> {

    @Override
    public Resource convert(Properties properties) {
        return new PropertiesResource(properties);
    }
}
