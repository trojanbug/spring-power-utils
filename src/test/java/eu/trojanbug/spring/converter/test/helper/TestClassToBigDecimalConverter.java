package eu.trojanbug.spring.converter.test.helper;

import org.springframework.core.convert.converter.Converter;

import java.math.BigDecimal;

public class TestClassToBigDecimalConverter implements Converter<TestClass,BigDecimal> {
    @Override
    public BigDecimal convert(TestClass source) {
        return source.getBigDecimal();
    }
}
