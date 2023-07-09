package com.example.springframe.beans.support;

import com.example.springframe.beans.BeanDefinition;
import com.example.springframe.beans.config.BeanDefinitionRegistry;
import com.example.springframe.beans.factory.ConfigurableListableBeanFactory;
import com.example.springframe.beans.proccessor.BeanPostProcessor;
import com.example.springframe.exception.BizException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/6/8
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry , ConfigurableListableBeanFactory {

    /**
     * 为什么要用线程安全的map
     */
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    @Override
    public void registryBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (Objects.isNull(beanDefinition)) {
            throw new BizException("com.example.springframe.bean not be defined");
        }
        return beanDefinition;
    }

    @Override
    public void preInstantiateSingletons() {

    }

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        beanPostProcessors.add(beanPostProcessor);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) {
        return null;
    }

    @Override
    public List<String> getBeanDefinitionNames() {
        return new ArrayList<>(beanDefinitionMap.keySet());
    }

    @Override
    public Collection<BeanPostProcessor> getBeanPostProcessors() {
        return beanPostProcessors;
    }
}
