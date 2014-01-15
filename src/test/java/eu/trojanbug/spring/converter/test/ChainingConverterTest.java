package eu.trojanbug.spring.converter.test;

import eu.trojanbug.spring.converter.test.helper.TestClass;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.util.Assert;
import org.testng.annotations.Test;

import javax.annotation.Resource;
import java.math.BigDecimal;


@Test
@ContextConfiguration(locations = "classpath:/spring/tests-config/chaining-converter-context.xml")
public class ChainingConverterTest extends AbstractTestNGSpringContextTests {

    @Resource(name = "conversionService")
    ConversionService conversionService;

    @Test
    public void testChainingConverter() {
        TestClass testResult = (TestClass) conversionService.convert("257", TypeDescriptor.valueOf(String.class), TypeDescriptor.valueOf(TestClass.class));
        Assert.notNull(testResult);
        Assert.isTrue(testResult.getBigDecimal().equals(BigDecimal.valueOf(257)));
    }


}
