package com.example.springframe.beans.factory;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/6/8
 */
public interface BeanFactory {

    /**
     * 获取bean实例对象
     *
     * @param beanName bean名称
     * @return
     */
    Object getBean(String beanName);

    /**
     * 实例化有参的bean对象
     *
     * @param beanName
     * @param args
     * @return
     */
    Object getBean(String beanName, Object... args);

    /**
     * 根据类型获取bean
     *
     * @param name
     * @param requireType
     * @param <T>
     * @return
     */
    <T> T getBeanOfType(String name, Class<T> requireType);
}
