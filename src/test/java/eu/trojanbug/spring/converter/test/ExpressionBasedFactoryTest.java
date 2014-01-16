package eu.trojanbug.spring.converter.test;

import eu.trojanbug.spring.builder.ExpressionBasedFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.util.Assert;
import org.testng.annotations.Test;

import javax.annotation.Resource;


@Test
@ContextConfiguration(locations = "classpath:/spring/tests-config/expression-based-factory-context.xml")
public class ExpressionBasedFactoryTest extends AbstractTestNGSpringContextTests {

    @Resource(name = "randomDouble")
    Object randomDouble;

    @Resource(name = "&randomDouble")
    ExpressionBasedFactory randomDoubleFactory;

    @Resource(name = "randomDoubleSingleton")
    Object randomDoubleSingleton;

    @Resource(name = "&randomDoubleSingleton")
    ExpressionBasedFactory randomDoubleSingletonFactory;

    @Resource(name = "stringReference")
    Object stringReference;

    @Test
    public void testExpressionBasedFactoryTypesDetection() {
        Assert.notNull(randomDouble);
        Assert.isTrue(randomDouble instanceof Double);
        Assert.isNull(randomDoubleFactory.getObjectType());

        Assert.notNull(randomDoubleSingleton);
        Assert.isTrue(randomDoubleSingleton instanceof Double);
        Assert.isAssignable(Double.class, randomDoubleSingletonFactory.getObjectType());
    }

    @Test
    public void testExpressionBasedFactoryBeanReferences() {
        Assert.notNull(stringReference);
        Assert.isTrue(stringReference.equals("Test"));
    }
}
