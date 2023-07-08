package com.example.springframe.beans.proccessor;

import com.example.springframe.beans.factory.ConfigurableListableBeanFactory;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/7/7
 */
public interface BeanFactoryPostProcessor {

    /**
     * 在BeanDefinition加载完成后实例化bean对象之前提供对BeanDefinition对修改
     * @param beanFactory
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory);
}
