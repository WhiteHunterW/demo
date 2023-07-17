package com.example.springframe.beans;

import com.example.springframe.beans.factory.ConfigurableBeanFactory;

/**
 * Function:
 * bean对象元数据
 * @author wenzeng
 * @date 2023/5/27
 */
public class BeanDefinition {

    String SCOPE_SINGLETON = ConfigurableBeanFactory.SCOPE_SINGLETON;
    String SCOPE_PROTOTYPE = ConfigurableBeanFactory.SCOPE_PROTOTYPE;

    /**
     * 对象
     */
    private Class beanClass;

    private PropertyValues propertyValues;

    private boolean singleton = true;

    private boolean protoType = false;

    private String scope = SCOPE_SINGLETON;

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

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
        this.singleton = SCOPE_SINGLETON.equals(scope);
        this.protoType = SCOPE_PROTOTYPE.equals(scope);
    }
}
