package com.example.biz.design.observer;

/**
 * 水质检测： 污染的不同程度市由不同的人员处理；即多个观察者
 * 观察者接口
 * @author wenzeng
 * @date 2025/1/21
 */
public interface WatcherObserver {


    /**
     * 观察者更新
     * @param subject
     */
    void update(WaterQualitySubject subject);


    /**
     * 设置观察人员的职务
     * @param job
     */
    void setJob(String job);

    /**
     * 获取观察人员的职务
     * @return
     */
    String getJob();


}
