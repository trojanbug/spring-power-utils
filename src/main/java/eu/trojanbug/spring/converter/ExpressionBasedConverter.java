package eu.trojanbug.spring.converter;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ExpressionBasedConverter implements GenericConverter, InitializingBean, BeanFactoryAware, ApplicationContextAware {

    private Set<ConvertiblePair> convertiblePairs = new HashSet<ConvertiblePair>();
    private Class sourceClass = null;
    private Class targetClass = null;
    private String conversionExpression;
    private Expression expressionCompiled;
    private BeanFactory beanFactory;
    private ApplicationContext applicationContext;
    private Map<String, Object> contextVariables;

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return convertiblePairs;
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        StandardEvaluationContext evaluationContext = new StandardEvaluationContext();
        //evaluationContext.setRootObject(source);
        evaluationContext.setVariable("source", source);
        evaluationContext.setVariable("sourceType", sourceType);
        evaluationContext.setVariable("targetType", targetType);
        if (beanFactory != null) {
            evaluationContext.setVariable("beanFactory", beanFactory);
        }
        if (applicationContext != null) {
            evaluationContext.setVariable("applicationContext", applicationContext);
        }
        if (contextVariables != null) {
            evaluationContext.setVariables(contextVariables);
        }

        return expressionCompiled.getValue(evaluationContext, targetType.getType());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (sourceClass != null && targetClass != null) {
            convertiblePairs = new HashSet<ConvertiblePair>();
            convertiblePairs.add(new ConvertiblePair(sourceClass, targetClass));
        }
        if (StringUtils.isEmpty(conversionExpression)) {
            throw new IllegalArgumentException("conversionException must not be null");
        }
        ExpressionParser parser = new SpelExpressionParser();
        expressionCompiled = parser.parseExpression(conversionExpression);
    }

    public Class getSourceClass() {
        return sourceClass;
    }

    public void setSourceClass(Class sourceClass) {
        this.sourceClass = sourceClass;
    }

    public Class getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class targetClass) {
        this.targetClass = targetClass;
    }

    public Set<ConvertiblePair> getConvertiblePairs() {
        return convertiblePairs;
    }

    public void setConvertiblePairs(Set<ConvertiblePair> convertiblePairs) {
        this.convertiblePairs = convertiblePairs;
    }

    public String getConversionExpression() {
        return conversionExpression;
    }

    public void setConversionExpression(String conversionExpression) {
        this.conversionExpression = conversionExpression;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public Map<String, Object> getContextVariables() {
        return contextVariables;
    }

    public void setContextVariables(Map<String, Object> contextVariables) {
        this.contextVariables = contextVariables;
    }
}
