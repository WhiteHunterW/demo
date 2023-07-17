package com.example.springframe.beans.factory;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/7/11
 */
public interface FactoryBean<T> {

    /**
     * 获取bean对象
     * @return
     */
    Object getObject();

    /**
     * 获取对象类型
     * @return T
     */
    T getObjectType();

    /**
     * 判断对象是否单例
     * @return
     */
    boolean isSingleton();
}
