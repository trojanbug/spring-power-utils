package eu.trojanbug.spring.builder;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.BeanResolver;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class ExpressionBasedFactory implements FactoryBean, BeanFactoryAware {

    private boolean singleton = false;
    private Class<?> objectType = null;
    private Object singletonInstance = null;
    private boolean lazilyInitialized = true;
    private static ExpressionParser parser = new SpelExpressionParser();
    private Expression expression = null;
    private BeanResolver beanResolver = null;
    private BeanFactory beanFactory = null;

    public void setFactoryExpressionString(String expression) {

        this.expression = parser.parseExpression(expression);
    }

    public String getFactoryExpressionString() {

        return this.expression.getExpressionString();
    }

    public Object getObject() throws Exception {
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setBeanResolver(beanResolver);

        if (objectType != null)
            return expression.getValue(context, objectType);
        else
            return expression.getValue(context);
    }

    public Class<?> getObjectType() {
        if (objectType != null) {
            return objectType;
        } else if (isSingleton() && !isLazilyInitialized()) {
            if (singletonInstance == null) {
                try {
                    singletonInstance = getObject();
                    if (singletonInstance != null) {
                        setObjectType(singletonInstance.getClass());
                    }
                    return objectType;
                } catch (Exception e) {
                    return null;
                }
            }
        }
        return null;
    }

    public void setObjectType(Class<?> objectType) {
        this.objectType = objectType;
    }

    public boolean isSingleton() {
        return singleton;
    }

    public void setSingleton(boolean singleton) {
        this.singleton = singleton;
    }

    public boolean isLazilyInitialized() {
        return lazilyInitialized;
    }

    public void setLazilyInitialized(boolean lazilyInitialized) {
        this.lazilyInitialized = lazilyInitialized;
    }

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
        beanResolver = new BeanFactoryResolver(beanFactory);
    }
}
