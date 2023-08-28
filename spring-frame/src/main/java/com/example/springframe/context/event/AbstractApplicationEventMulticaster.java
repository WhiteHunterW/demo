package com.example.springframe.context.event;

import com.example.common.BizException;
import com.example.springframe.beans.BeanFactoryAware;
import com.example.springframe.beans.factory.BeanFactory;
import com.example.springframe.context.ApplicationEvent;
import com.example.springframe.context.ApplicationListener;
import org.springframework.util.ClassUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/7/31
 */
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {

    public Set<ApplicationListener<ApplicationEvent>> applicationListeners = new LinkedHashSet<>();

    private BeanFactory beanFactory;

    @Override
    public void addApplicationListener(com.example.springframe.context.ApplicationListener<?> listener) {
        applicationListeners.add((ApplicationListener<ApplicationEvent>) listener);
    }

    @Override
    public void removeApplicationListener(com.example.springframe.context.ApplicationListener<?> listener) {
        applicationListeners.remove(listener);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    /**
     * 获取所有满足条件的监听器
     * @param event
     * @return
     */
    protected Collection<ApplicationListener> getApplicationListeners(ApplicationEvent event) {
        LinkedList<ApplicationListener> listeners = new LinkedList<>();
        for (ApplicationListener listener : applicationListeners) {
            if(supportEvent(listener, event)) {
                listeners.add(listener);
            }
        }
        return listeners;
    }

    /**
     * 判断监听器是否支持某种事件类型
     * ???
     * @param listener
     * @param event
     * @return
     */
    protected boolean supportEvent(ApplicationListener listener, ApplicationEvent event) {
        Class<? extends ApplicationListener> listenerClass = listener.getClass();
        Class<?> targetClass = ClassUtils.isCglibProxyClass(listenerClass) ? listenerClass.getClass() : listenerClass;
        Type genericInterface = targetClass.getGenericInterfaces()[0];
        Type actualArgument = ((ParameterizedType) genericInterface).getActualTypeArguments()[0];
        String className = actualArgument.getTypeName();
        Class<?> eventClassName;
        try {
            eventClassName = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new BizException("wrong event class name : " + className);
        }
        return eventClassName.isAssignableFrom(event.getClass());
    }
}
