package com.example.springframe.beans.factory;

import com.example.springframe.beans.BeanDefinition;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/7/2
 */
public interface ConfigurableListableBeanFactory extends ConfigurableBeanFactory{


    /**
     * 获取bean元数据
     * @param beanName
     * @return
     */
    BeanDefinition getBeanDefinition(String beanName);

}
