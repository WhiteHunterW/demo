package com.example.springframe.beans;

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

    /**
     * 配置在xml文件里的？
     */
    private String initMethodName;

    private String destroyMethodName;

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

    public String getInitMethodName() {
        return initMethodName;
    }

    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    public String getDestroyMethodName() {
        return destroyMethodName;
    }

    public void setDestroyMethodName(String destroyMethodName) {
        this.destroyMethodName = destroyMethodName;
    }
}
