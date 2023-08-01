package com.example.springframe.context.event;

import com.example.springframe.context.ApplicationEvent;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/7/31
 */
public class ApplicationContextEvent extends ApplicationEvent {

    /**
     *
     * @param source
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    /**
     * 获取事件上下文
     * @return
     */
    public final ApplicationContextEvent getApplicationEvent() {
        return (ApplicationContextEvent) getSource();
    }
}
