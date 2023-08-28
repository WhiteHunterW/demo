package com.example.springframe.beans.support;

import cn.hutool.core.util.StrUtil;
import com.example.common.BizException;
import com.example.springframe.beans.BeanClassLoaderAware;
import com.example.springframe.beans.BeanDefinition;
import com.example.springframe.beans.BeanFactoryAware;
import com.example.springframe.beans.BeanNameAware;
import com.example.springframe.beans.BeanReReference;
import com.example.springframe.beans.PropertyValue;
import com.example.springframe.beans.PropertyValues;
import com.example.springframe.beans.factory.AutowireCapableBeanFactory;
import com.example.springframe.beans.proccessor.BeanPostProcessor;
import com.example.springframe.beans.strategy.CglibSubclassingInstantiationStrategy;
import com.example.springframe.beans.strategy.InstantiationStrategy;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);
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
        registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);
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
    protected Object initializationBean(String beanName, Object bean, BeanDefinition beanDefinition) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        // 先执行容器相关的标识接口 关于BeanFactory的几个aware实现类在initBean方法里直接判断 获取容器相关的信息
        // ApplicationContextAware 相关的子接口会在BeanPostProcess执行的过程判断 因为是两种容器？
        // 对ApplicationContextAware 执行是放在前置处理器里的
        if(bean instanceof BeanFactoryAware) {
            ((BeanFactoryAware) bean).setBeanFactory(this);
        }
        if(bean instanceof BeanNameAware) {
            ((BeanNameAware) bean).setBeanName(beanName);
        }
        if(bean instanceof BeanClassLoaderAware) {
            ((BeanClassLoaderAware) bean).setBeanClassLoader(getBeanClassLoader());
        }

        // 1.执行BeanPostProcessor before
        Object wrapperBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

        // 调用init-method 执行初始化
        invokeInitMethods(beanName, wrapperBean, beanDefinition);

        // 2.执行BeanPostProcessor after
        wrapperBean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
        return wrapperBean;
    }


    private void invokeInitMethods(String beanName, Object wrapperBean, BeanDefinition beanDefinition) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 1. 实现接口InitializingBean
        if(wrapperBean instanceof  InitializingBean) {
            /*
             *在bean对象执行完属性设置之后，需要增加比如容器获取等逻辑的可以让类实现InitializingBean接口 重写afterPropertiesSet方法
             * 比如使用工厂类去获取某个类下的所有bean对象 放入map?
             */
            ((InitializingBean) wrapperBean).afterPropertiesSet();
        }

        // 2.配置细腻 init-method
        String initMethodName = beanDefinition.getInitMethodName();
        if(StrUtil.isNotEmpty(initMethodName)) {
            // 判断配置的初始化方法名跟类里实际定义的方法名是否是同一个
            Method initMethod = beanDefinition.getBeanClass().getMethod(initMethodName);
            if(null == initMethod) {
                throw new BizException("could not find an init method named '" + initMethod + "' on bean with name '"+beanName+"'");
            }
            // 这是在提前调用init-method方法？？ 可以怎么应用呢？
            initMethod.invoke(wrapperBean);
        }


    }


    protected void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition definition)  {
        if(bean instanceof DisposableBean || StrUtil.isNotEmpty(definition.getDestroyMethodName())) {
            registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, definition));
        }
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
