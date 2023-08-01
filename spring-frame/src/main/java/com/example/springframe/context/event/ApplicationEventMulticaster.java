package com.example.springframe.context.event;

import com.example.springframe.context.ApplicationEvent;
import com.example.springframe.context.ApplicationListener;

/**
 * Function:
 * 事件广播器
 * @author wenzeng
 * @date 2023/7/31
 */
public interface ApplicationEventMulticaster {

    /**
     * 添加事件监听器
     * @param listener
     */
    void addApplicationListener(ApplicationListener<?> listener);

    /**
     * 移除事件监听器
     * @param listener
     */
    void removeApplicationListener(ApplicationListener<?> listener);

    /**
     * 广播事件上下文
     * @param event
     */
    void multicastEvent(ApplicationEvent event);
}
