package com.example.springframe.beans.support;

import com.example.springframe.beans.BeanDefinition;
import com.example.springframe.beans.BeanReReference;
import com.example.springframe.beans.PropertyValue;
import com.example.springframe.beans.PropertyValues;
import com.example.springframe.beans.factory.AutowireCapableBeanFactory;
import com.example.springframe.beans.proccessor.BeanPostProcessor;
import com.example.springframe.beans.strategy.CglibSubclassingInstantiationStrategy;
import com.example.springframe.beans.strategy.InstantiationStrategy;
import com.example.springframe.exception.BizException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/6/8
 */
@Slf4j
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {


    private BeanPostProcessor postProcessor;

    /**
     * 为什么是直接写死使用cglib这个策略？
     * spring在实例化多构造参数的bean对象是怎么来选择执行策略的？
     */
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();


    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) {
        Object bean = null;
        try {
            bean = beanDefinition.getBeanClass().newInstance();
            // 设置对象属性
            applyBeanPropertyValues(beanName, bean, beanDefinition);
            bean = initializationBean(beanName, bean, beanDefinition);
        } catch (InstantiationException | IllegalAccessException | NoSuchFieldException e) {
            log.error("create bean error ", e);
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
            // 设置对象属性
            applyBeanPropertyValues(beanName, bean, beanDefinition);
            bean = initializationBean(beanName, bean, beanDefinition);
        } catch (InvocationTargetException | InstantiationException
                | IllegalAccessException | NoSuchMethodException | NoSuchFieldException e) {
            throw new BizException("create bean error");
        }
        addSingletonBean(beanName, bean);
        return bean;
    }

    protected Object createBeanInstance(String beanName, BeanDefinition beanDefinition, Object[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Constructor constructor = null;
        Class<?> cl = beanDefinition.getBeanClass();
        Constructor<?>[] constructors = cl.getDeclaredConstructors();
        for (Constructor c : constructors) {
            if (null != args && args.length == c.getParameterTypes().length) {
                constructor = c;
                break;
            }
        }
        return instantiationStrategy.instantiate(beanDefinition, beanName, constructor, args);
    }

    /**
     * 设置对象属性
     *
     * @param beanName
     * @param obj
     * @param beanDefinition
     */
    protected void applyBeanPropertyValues(String beanName, Object obj, BeanDefinition beanDefinition) throws IllegalAccessException, NoSuchFieldException {
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        if (null == propertyValues) {
            return;
        }
        /*Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = propertyValues.getPropertyValue(field.getName());
            field.set(obj, value);
        }*/
        for (PropertyValue pv : propertyValues.getPropertyValueList()) {
            String name = pv.getName();
            Object value = pv.getValue();
            if (value instanceof BeanReReference) {
                BeanReReference reReference = (BeanReReference) value;
                value = getBean(reReference.getBeanName());
            }
            Class<?> cl = obj.getClass();
            // 反射根据name获取不到字段会直接抛NoSuchField的异常
            Field field = cl.getDeclaredField(name);
            field.setAccessible(true);
            field.set(obj, value);
        }
    }


    /**
     * 实例化bean
     * @param beanName
     * @param bean
     * @param beanDefinition
     */
    protected Object initializationBean(String beanName, Object bean, BeanDefinition beanDefinition){
        // 添加前置和后置处理器
        // 1.执行BeanPostProcessor before
        Object wrapperBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

        // TODO
        invokeInitMethods(beanName, wrapperBean, beanDefinition);

        // 2.执行BeanPostProcessor after
        wrapperBean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
        return wrapperBean;
    }


    private void invokeInitMethods(String beanName, Object wrapperBean, BeanDefinition beanDefinition) {

    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object bean, String beanName) {
        Object result = bean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            Object current = beanPostProcessor.postProcessAfterInitialization(result, beanName);
            if(null == current) {
                return result;
            }
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object bean, String beanName) {
        Object result = bean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            Object current = beanPostProcessor.postProcessBeforeInitialization(result, beanName);
           if(null == current) {
               // 只要有一个处理器处理的结果为空就直接返回？？？？
               return result;
           }
           result = current;
        }
        return result;
    }
}
