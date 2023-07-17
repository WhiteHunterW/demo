package com.example.springframe.beans.support;

import com.example.springframe.beans.factory.FactoryBean;

import java.util.Map;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/7/11
 */
public class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry{

    private Map<String, Object> factoryBeanObjectCache;

    /**
     * 根据beanName获取内存里的对象
     * @param beanName
     * @return
     */
    protected Object getCachedObjectForFactoryBean(String beanName) {
        return null;
    }


    /**
     * 从指定容器里获取指定对象
     * @param factoryBean
     * @param beanName
     * @return
     */
    protected Object getObjectFromFactoryBean(FactoryBean factoryBean, String beanName){
        return null;
    }

    public Object doGetObjectFromFactoryBean(final FactoryBean factoryBean, final String beanName) {
        return null;
    }
}
