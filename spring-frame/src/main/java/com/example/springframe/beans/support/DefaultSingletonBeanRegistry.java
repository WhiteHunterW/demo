package com.example.springframe.beans.support;

import com.example.springframe.beans.config.SingletonBeanRegistry;
import com.example.springframe.beans.factory.ConfigurableBeanFactory;
import com.example.springframe.exception.BizException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/6/8
 */
@Slf4j
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry, ConfigurableBeanFactory {

    private Map<String, Object> singletonBeanMap = new HashMap<>();

    private Map<String, DisposableBean> disposableBeanMap = new HashMap<>();

    private Object NULL_OBJECT;

    @Override
    public Object getSingletonBean(String beanName) {
        return singletonBeanMap.get(beanName);
    }

    /**
     * 添加单例bean
     * 单例bean有单独的容器记录
     *
     * @param beanName
     * @param singletonBean
     */
    protected void addSingletonBean(String beanName, Object singletonBean) {
        singletonBeanMap.put(beanName, singletonBean);
    }

    /**
     * 注册销毁bean的适配器
     * @param beanName
     * @param disposableBeanAdapter
     */
    protected void registerDisposableBean(String beanName, DisposableBeanAdapter disposableBeanAdapter) {
        disposableBeanMap.put(beanName, disposableBeanAdapter);
    }


    @Override
    public Object getBean(String beanName) {
        return null;
    }

    @Override
    public Object getBean(String beanName, Object... args) {
        return null;
    }

    @Override
    public <T> T getBeanOfType(String name, Class<T> requireType) {
        return null;
    }

    @Override
    public void destroySingletons() {
        try {
            for (DisposableBean disposableBean : disposableBeanMap.values()) {
                disposableBean.destroy();
            }
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            log.error("destroySingletons ", e);
            throw new BizException("destroy error");
        }
    }
}
