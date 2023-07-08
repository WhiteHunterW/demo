package com.example.springframe.context;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/7/7
 */
public interface ConfigurableApplicationContext extends ApplicationContext{

    /**
     * 刷新容器
     */
    void refresh();
}
