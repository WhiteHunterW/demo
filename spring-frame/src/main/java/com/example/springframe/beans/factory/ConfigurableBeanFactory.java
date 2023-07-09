package com.example.springframe.beans.factory;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/7/2
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory{

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";


    /**
     * 销毁单例bean
     */
    void destroySingletons();
}
