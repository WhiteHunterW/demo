package com.example.springframe.beans.support;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/7/9
 */
public interface InitializingBean {

    /**
     * 实例化bean对象
     * 填充bean对象属性值后调用
     */
    void afterPropertiesSet();
}
