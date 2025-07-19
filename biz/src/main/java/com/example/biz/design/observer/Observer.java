package com.example.biz.design.observer;

/**
 * @author wenzeng
 * @date 2025/1/21
 */
public interface Observer {

    /**
     * 观察者根据目标对象的修改更新接口
     * @param subject 传入目标对象便于获取目标对象的状态
     */
    void update(Subject subject);
}


