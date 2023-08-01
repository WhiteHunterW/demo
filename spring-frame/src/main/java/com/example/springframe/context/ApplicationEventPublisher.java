package com.example.springframe.context;

import com.example.springframe.context.ApplicationEvent;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/7/31
 */
public interface ApplicationEventPublisher {

    /**
     * 发布事件
     * @param event
     */
    void publishEvent(ApplicationEvent event);
}
