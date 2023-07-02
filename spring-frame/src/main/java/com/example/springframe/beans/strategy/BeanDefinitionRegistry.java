package com.example.springframe.beans.strategy;

import com.example.springframe.beans.BeanDefinition;

import java.util.List;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/7/2
 */
public interface BeanDefinitionRegistry {

    /**
     * 注册bean元数据
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * 获取bean元数据
     * @param beanName
     * @return
     */
    BeanDefinition getBeanDefinition(String beanName);

    /**
     * 判断是否包含bean元数据
     * @param beanName
     * @return
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * 获取所有注册的bean名称
     * @return
     */
    List<String> getBeanDefinitionNames();


}
