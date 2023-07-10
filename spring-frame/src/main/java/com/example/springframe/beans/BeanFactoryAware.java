package com.example.springframe.beans;

import com.example.springframe.beans.factory.BeanFactory;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/7/10
 */
public interface BeanFactoryAware extends Aware{

    /**
     * 设置bean容器
     * @param beanFactory
     */
    void setBeanFactory(BeanFactory beanFactory);
}
