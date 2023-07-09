package com.example.springframe.beans.factory;

import com.example.springframe.beans.proccessor.BeanPostProcessor;

import java.util.Collection;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/7/2
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     * bean实例化的时候执行后置处理
     *
     * @param bean
     * @param beanName
     * @return
     */
    Object applyBeanPostProcessorsAfterInitialization(Object bean, String beanName);


    /**
     * bean实例化的时候执行前置处理
     *
     * @param bean
     * @param beanName
     * @return
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object bean, String beanName);

    /**
     * 向BeanFactory里添加前置处理器
     *
     * @param beanPostProcessor
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);


    /**
     * 获取处理器
     * @return
     */
    Collection<BeanPostProcessor> getBeanPostProcessors();
}
