package com.example.biz.design.observer;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wenzeng
 * @date 2025/1/21
 */
@Slf4j
public class Watcher implements WatcherObserver {

    /**
     * 观察者职务
     */
    private String job;


    @Override
    public void update(WaterQualitySubject subject) {
        log.info("当前处理人员:{}, 水质污染程度:{}", job, subject.getPolluteLevel());
    }

    @Override
    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String getJob() {
        return job;
    }


}
