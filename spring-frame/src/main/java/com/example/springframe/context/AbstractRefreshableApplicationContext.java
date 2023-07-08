package com.example.springframe.context;

import com.example.springframe.beans.support.DefaultListableBeanFactory;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/7/7
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext{

    private DefaultListableBeanFactory beanFactory;


    @Override
    protected void refreshBeanFactory() {

    }

    /**
     * 加载beanDefinition定义？
     * @param beanFactory
     */
    public abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);
}
