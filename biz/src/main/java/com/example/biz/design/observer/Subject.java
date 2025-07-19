package com.example.biz.design.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式 目标对象顶层类
 * 观察者模式用于一个对象的改变影响到另一个对象的场景
 * @author wenzeng
 * @date 2025/1/21
 */
public class Subject {

    /**
     * 观察者列表
     */
    private final List<Observer> observers = new ArrayList<>();


    /**
     * 添加观察者
     * @param observer
     */
    public void attach(Observer observer) {
        observers.add(observer);
    }

    /**
     * 删除观察者
     * @param observer
     */
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    /**
     * 通知所有观察者
     */
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }




}
