package com.example.springframe.service;

import com.example.springframe.beans.BeanDefinition;
import com.example.springframe.beans.PropertyValue;
import com.example.springframe.beans.PropertyValues;
import com.example.springframe.beans.factory.ConfigurableListableBeanFactory;
import com.example.springframe.beans.proccessor.BeanFactoryPostProcessor;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/7/8
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userServiceImpl");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();

        PropertyValue propertyValue = new PropertyValue("location", "中国北京西城区");
        propertyValues.addPropertyValue(propertyValue);
    }
}
