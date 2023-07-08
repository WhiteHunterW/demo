package com.example.springframe.beans.strategy;

import com.example.springframe.beans.config.BeanDefinitionRegistry;
import com.example.springframe.core.io.DefaultResourceLoader;
import com.example.springframe.core.io.ResourceLoader;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/7/2
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{

    private BeanDefinitionRegistry registry;

    private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
       this(registry, new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return this.registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return this.resourceLoader;
    }
}
