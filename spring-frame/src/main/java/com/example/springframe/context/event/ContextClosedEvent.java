package com.example.springframe.context.event;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/7/31
 */
public class ContextClosedEvent extends ApplicationContextEvent{
    public ContextClosedEvent(Object source) {
        super(source);
    }
}
