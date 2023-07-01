package com.example.springframe.bean;

/**
 * Function:
 * bean对象元数据
 * @author wenzeng
 * @date 2023/5/27
 */
public class BeanDefinition {

    /**
     * 对象
     */
    private Class beanClass;

    public BeanDefinition(Class bean) {
        this.beanClass = bean;
    }

    public Class getBeanClass() {
        return beanClass;
    }
}
