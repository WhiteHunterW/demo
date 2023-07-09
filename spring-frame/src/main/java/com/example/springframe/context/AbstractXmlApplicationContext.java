package com.example.springframe.context;

import com.example.springframe.beans.strategy.XmlBeanDefinitionReader;
import com.example.springframe.beans.support.DefaultListableBeanFactory;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/7/7
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] locations = getConfigLocations();
        beanDefinitionReader.loadBeanDefinitions(locations);
    }

    /**
     * 获取资源地址
     * @return
     */
    protected abstract String[] getConfigLocations();

}
