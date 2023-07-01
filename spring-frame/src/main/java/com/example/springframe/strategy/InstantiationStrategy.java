package com.example.springframe.strategy;

import com.example.springframe.bean.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/6/28
 */
public interface InstantiationStrategy {

    /**
     * 实例化bean对象
     * @param beanDefinition
     * @param beanName
     * @param constructor
     * @param args
     * @return
     */
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;


}
