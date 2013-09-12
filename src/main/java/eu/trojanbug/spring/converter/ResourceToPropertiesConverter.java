package eu.trojanbug.spring.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ResourceToPropertiesConverter implements Converter<Resource, Properties> {

    @Override
    public Properties convert(Resource resource) {
        Properties props = new Properties();
        if (resource != null) {
            InputStream resourceIs = null;
            try {
                resourceIs = resource.getInputStream();
                props.load(resourceIs);
            } catch (IOException e) {
                if (resourceIs != null)
                    try {
                        resourceIs.close();
                    } catch (IOException e1) {
                        // ignore
                    }
            }
        }

        return props;
    }
}
