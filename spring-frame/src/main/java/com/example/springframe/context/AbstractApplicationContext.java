package com.example.springframe.context;

import com.example.springframe.beans.factory.ConfigurableListableBeanFactory;
import com.example.springframe.beans.proccessor.BeanFactoryPostProcessor;
import com.example.springframe.beans.proccessor.BeanPostProcessor;
import com.example.springframe.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/7/7
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    @Override
    public void refresh() {
        // 1.创建BeanFactory, 并加载BeanDefinition
        refreshBeanFactory();
        // 2.获取BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        // 3.bean实例化前执行BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessors(beanFactory);
        // 4.bean实例化前执行BeanPostProcessor
        registerBeanPostProcessors(beanFactory);
        // 5.提前实例化单例Bean对象

    }

    /**
     * 刷新Bean工厂
     */
    protected abstract void refreshBeanFactory();

    /**
     * 获取BeanFactory
     *
     * @return
     */
    protected abstract ConfigurableListableBeanFactory getBeanFactory();


    /**
     * 调用BeanFactory的前置处理器
     * 在Bean对象实例化之前对BeanDefinition修改
     *
     * @param beanFactory
     */
    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryBeansMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryBeansMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    /**
     * 注册bean对象对前置处理器
     *
     * @param beanFactory
     */
    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }


}
