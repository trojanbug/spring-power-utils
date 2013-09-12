package eu.trojanbug.spring.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.convert.ConversionService;

public class ConversionServiceSettingBeanDefinitionRegistryPostProcessor implements BeanFactoryPostProcessor, PriorityOrdered {

    private ConversionService conversionService;
    private int order = Ordered.HIGHEST_PRECEDENCE;

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        beanFactory.setConversionService(conversionService);
    }


    public ConversionService getConversionService() {
        return conversionService;
    }

    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
