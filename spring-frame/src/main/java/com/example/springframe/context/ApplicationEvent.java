package com.example.springframe.context;

import java.util.EventObject;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/7/31
 */
public abstract class ApplicationEvent extends EventObject {


    public ApplicationEvent(Object source) {
        super(source);
    }

}
