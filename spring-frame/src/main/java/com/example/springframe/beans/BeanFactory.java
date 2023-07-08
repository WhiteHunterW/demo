package com.example.springframe.beans;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Function:
 * bean工厂, 管理bean对象
 * 首先得有一个容器，通过容器完成对bean的管理：放入和获取
 * @author wenzeng
 * @date 2023/5/27
 */
public class BeanFactory {

    /**
     * 为什么要用线程安全的map
     */
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    /**
     * 获取bean的元数据
     * @param beanName
     * @return
     */
    public BeanDefinition getBean(String beanName) {
        return beanDefinitionMap.get(beanName);
    }

    /**
     * 注册bean
     * @param beanName
     * @param beanDefinition
     */
    public void registerBean(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

}
