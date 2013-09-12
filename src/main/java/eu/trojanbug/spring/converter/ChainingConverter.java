package eu.trojanbug.spring.converter;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.core.convert.support.DefaultConversionService;

import java.util.*;

public class ChainingConverter implements GenericConverter {

    private List<Class> conversionPath;
    private ConversionService conversionService = new DefaultConversionService();

    public Set<ConvertiblePair> getConvertibleTypes() {
        ConvertiblePair cp = new ConvertiblePair(conversionPath.get(0),conversionPath.get(conversionPath.size()-1));
        return new HashSet<ConvertiblePair>(Arrays.asList(cp));
    }

    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        Object target = source;
        for (Class nextClass : conversionPath) {
          target = conversionService.convert(target,nextClass);
        }
        return target;
    }

    public List<Class> getConversionPath() {
        return conversionPath;
    }

    public void setConversionPath(List<Class> conversionPath) {
        this.conversionPath = conversionPath;
    }

    public ConversionService getConversionService() {
        return conversionService;
    }

    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }
}
