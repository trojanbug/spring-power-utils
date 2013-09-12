package eu.trojanbug.spring.converter.test;

import eu.trojanbug.spring.converter.ExpressionBasedConverter;
import eu.trojanbug.spring.converter.test.helper.TestClass;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.util.Assert;
import org.testng.annotations.Test;

import javax.annotation.Resource;
import java.util.Properties;


@Test
@ContextConfiguration(locations = "classpath:/spring/tests-config/property-resource-context.xml")
public class PropertiesResourcesTest extends AbstractTestNGSpringContextTests {

    @Resource(name = "testBean1")
    TestClass testBean1;

    @Resource(name = "targetProperties")
    Properties targetProperties;

    @Test
    public void testPropertyResources() {
        Assert.notNull(testBean1);
        Assert.isTrue(testBean1.getInt() == 50);

        Assert.notNull(targetProperties);
        Assert.isTrue(targetProperties.getProperty("testProperty2").equals("100"));
    }


}
