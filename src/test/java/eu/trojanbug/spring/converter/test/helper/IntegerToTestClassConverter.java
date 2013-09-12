package eu.trojanbug.spring.converter.test.helper;

import org.springframework.core.convert.converter.Converter;

public class IntegerToTestClassConverter implements Converter<Integer,TestClass> {
    @Override
    public TestClass convert(Integer source) {
        return new TestClass(source);
    }
}
