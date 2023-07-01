package com.example.springframe.strategy;

import com.example.springframe.bean.BeanDefinition;
import com.example.springframe.exception.BizException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/6/28
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) {
        Class cl = beanDefinition.getBeanClass();
        try {
            if(constructor != null) {
                return cl.getDeclaredConstructor(constructor.getParameterTypes()).newInstance(args);
            } else {
                return cl.getConstructor().newInstance();
            }
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new BizException("failed to instantiate");
        }
    }
}
