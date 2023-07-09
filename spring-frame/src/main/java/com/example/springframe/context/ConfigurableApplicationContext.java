package com.example.springframe.context;

import java.lang.reflect.InvocationTargetException;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/7/7
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * 刷新容器
     */
    void refresh();


    /**
     * 注册关闭的钩子方法
     */
    void registerShutdownHook();

    /**
     * bean的结束方法
     */
    void close() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException;
}
