package com.example.springframe.strategy;

import com.example.springframe.bean.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/6/28
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) {

        return null;
    }
}
