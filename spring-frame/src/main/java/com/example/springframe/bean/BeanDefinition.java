package com.example.springframe.bean;

import com.example.springframe.PropertyValues;

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

    private PropertyValues propertyValues;

    public BeanDefinition(Class bean) {
        this.beanClass = bean;
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }
}
