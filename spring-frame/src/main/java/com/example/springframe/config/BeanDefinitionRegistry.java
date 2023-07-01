package com.example.springframe.config;

import com.example.springframe.bean.BeanDefinition;

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
}
