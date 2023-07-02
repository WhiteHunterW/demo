package com.example.springframe.beans.support;

import com.example.springframe.beans.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/6/8
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String, Object> singletonBeanMap = new HashMap<>();

    @Override
    public Object getSingletonBean(String beanName) {
        return singletonBeanMap.get(beanName);
    }

    /**
     * 添加单例bean
     * 单例bean有单独的容器记录
     * @param beanName
     * @param singletonBean
     */
    protected void addSingletonBean(String beanName, Object singletonBean) {
        singletonBeanMap.put(beanName, singletonBean);
    }
}
