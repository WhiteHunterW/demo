package com.example.springframe.beans.support;

import java.lang.reflect.InvocationTargetException;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/7/9
 */
public interface DisposableBean {

    /**
     * 销毁bean
     */
    void destroy() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
}
