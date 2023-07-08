package com.example.springframe.context;

import com.example.springframe.beans.factory.BeanFactory;
import com.example.springframe.beans.factory.ConfigurableListableBeanFactory;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/7/7
 */
public abstract class AbstractApplicationContext implements ConfigurableApplicationContext {

    @Override
    public void refresh() {



    }

    /**
     * 刷新Bean工厂
     */
    protected abstract void refreshBeanFactory();

    /**
     * 获取BeanFactory
     * @return
     */
    protected abstract BeanFactory getBeanFactory();


    /**
     * 调用BeanFactory的前置处理器
     * 在Bean对象实例化之前对BeanDefinition修改
     * @param beanFactory
     */
    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {

    }

    /**
     * Bean对象实例化后对Bean对象进行修改
     * @param beanFactory
     */
    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {

    }



}
