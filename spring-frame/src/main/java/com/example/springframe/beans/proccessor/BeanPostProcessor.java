package com.example.springframe.beans.proccessor;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/7/7
 */
public interface BeanPostProcessor {

    /**
     * 前置处理器
     * 对象初始化之前执行
     * @param bean
     * @param beanName
     * @return
     */
    Object postProcessBeforeInitialization(Object bean, String beanName);

    /**
     * 后置处理器
     * 对象实例化后执行
     * @param bean
     * @param beanName
     * @return
     */
    Object postProcessAfterInitialization(Object bean, String beanName);
}
