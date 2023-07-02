package com.example.springframe.beans.support;

import com.example.springframe.beans.BeanDefinition;
import com.example.springframe.beans.config.BeanDefinitionRegistry;
import com.example.springframe.exception.BizException;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/6/8
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {

    /**
     * 为什么要用线程安全的map
     */
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    @Override
    public void registryBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    protected BeanDefinition getBeanDefinition(String beanName) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (Objects.isNull(beanDefinition)) {
            throw new BizException("com.example.springframe.bean not be defined");
        }
        return beanDefinition;
    }
}
