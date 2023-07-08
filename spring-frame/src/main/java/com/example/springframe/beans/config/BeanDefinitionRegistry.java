package com.example.springframe.beans.config;

import com.example.springframe.beans.BeanDefinition;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/6/8
 */
public interface BeanDefinitionRegistry {

    /**
     * 注册bean
     * @param beanName
     * @param beanDefinition
     */
    void registryBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * 判断是否包含bean元数据
     * @param beanName
     * @return
     */
    boolean containsBeanDefinition(String beanName);
}
