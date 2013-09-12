package eu.trojanbug.spring.converter.test;

import eu.trojanbug.spring.converter.ExpressionBasedConverter;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.util.Assert;
import org.testng.annotations.Test;

import javax.annotation.Resource;


@Test
@ContextConfiguration(locations = "classpath:/spring/tests-config/expression-based-converter-context.xml")
public class ExpressionBasedConverterTest extends AbstractTestNGSpringContextTests {

    @Resource(name = "expressionBasedConverter")
    ExpressionBasedConverter expressionBasedConverter;

    @Test
    public void testExpressionBasedConverter() {
        Object testResult = expressionBasedConverter.convert("5", TypeDescriptor.valueOf(String.class), TypeDescriptor.valueOf(Long.class));
        Assert.notNull(testResult);
        Assert.isTrue(testResult.equals(50L));
    }


}
