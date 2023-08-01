package com.example.springframe.context.event;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/7/31
 */
public class ContextRefreshedEvent extends ApplicationContextEvent{

    /**
     * construct
     * @param source
     */
    public ContextRefreshedEvent(Object source) {
        super(source);
    }
}
