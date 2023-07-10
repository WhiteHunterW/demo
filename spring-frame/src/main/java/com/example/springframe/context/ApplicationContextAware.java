package com.example.springframe.context;

import com.example.springframe.beans.Aware;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/7/10
 */
public interface ApplicationContextAware extends Aware {


    /**
     * 给bean设置容器
     * 即于获取bean的容器
     * @param applicationContext
     */
    void setApplicationContext(ApplicationContext applicationContext);
}
