package com.example.springframe.context.support;

import com.example.springframe.beans.proccessor.BeanPostProcessor;
import com.example.springframe.context.ApplicationContext;
import com.example.springframe.context.ApplicationContextAware;

/**
 * Function:
 * 修改Bean的一个默认实现类
 * @author wenzeng
 * @date 2023/7/10
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;


    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if(bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }
}
