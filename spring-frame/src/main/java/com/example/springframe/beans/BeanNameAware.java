package com.example.springframe.beans;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/7/10
 */
public interface BeanNameAware extends Aware{

    /**
     * 设置bean对象名称
     * @param beanName
     */
    void setBeanName(String beanName);
}
