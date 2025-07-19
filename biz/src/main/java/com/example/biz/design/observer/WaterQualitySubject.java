package com.example.biz.design.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 水质监测的目标对象
 * 水质污染的程度不同，由不同的观察人员处理（对应不同的观察者逻辑）
 * @author wenzeng
 * @date 2025/1/21
 */
public abstract class WaterQualitySubject {

    protected List<WatcherObserver> observers = new ArrayList<>();

    public void attach(WatcherObserver observer) {
        observers.add(observer);
    }

    public void delete(WatcherObserver observer) {
        observers.remove(observer);
    }


    /**
     * 通知观察者
     */
    public abstract void notifyObservers();

    /**
     * 获取水质污染级别
     * @return
     */
    public abstract int getPolluteLevel();

}
