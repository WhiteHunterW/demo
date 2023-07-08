package com.example.springframe.beans.strategy;

import com.example.springframe.beans.BeanDefinition;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

/**
 * Function:
 * 使用Cglib 实现有参构造实例化
 * @author wenzeng
 * @date 2023/6/28
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback(
                new NoOp() {
                    @Override
                    public int hashCode() {
                        return super.hashCode();
                    }
                }
        );
        if(null == constructor) {
            return enhancer.create();
        }
        return enhancer.create(constructor.getParameterTypes(), args);
    }
}
