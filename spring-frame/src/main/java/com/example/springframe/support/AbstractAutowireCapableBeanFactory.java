package com.example.springframe.support;

import com.example.springframe.bean.BeanDefinition;
import com.example.springframe.exception.BizException;
import com.example.springframe.strategy.CglibSubclassingInstantiationStrategy;
import com.example.springframe.strategy.InstantiationStrategy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/6/8
 */

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{

    /**
     * 为什么是直接写死使用cglib这个策略？
     * spring在实例化多构造参数的bean对象是怎么来选择执行策略的？
     */
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) {
        Object bean = null;
        try {
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
         throw new BizException("create com.example.springframe.bean error");
        }
        // 放入单例容器？
        addSingletonBean(beanName, bean);
        return bean;
    }

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) {
        /*
         * 后面流程里都没有用到beanName,还有必要把这个参数传进去吗？
         * beanName在创建bean的过程中使用的点在于，创建完成后将bean以key-value的方式添加进Map
         */
        Object bean = null;
        try {
            bean = createBeanInstance(beanName, beanDefinition, args);
        } catch (InvocationTargetException | InstantiationException |IllegalAccessException | NoSuchMethodException e) {
            throw new BizException("create bean error");
        }
        addSingletonBean(beanName, bean);
        return bean;
    }


    protected Object createBeanInstance(String beanName, BeanDefinition beanDefinition, Object[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Constructor constructor = null;
        Class<?> cl = beanDefinition.getBeanClass();
        Constructor<?> [] constructors = cl.getDeclaredConstructors();
        for (Constructor c : constructors) {
            if(null != args && args.length == c.getParameterTypes().length){
                constructor = c;
                break;
            }
        }
        return instantiationStrategy.instantiate(beanDefinition, beanName, constructor, args);
    }
}
