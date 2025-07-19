package com.example.biz.design.observer;

/**
 * 具体的目标对象
 * 记录目标对象的状态变化
 * @author wenzeng
 * @date 2025/1/21
 */
public class ConcreteSubject extends Subject{

    /**
     * 目标对象的状态
     */
    private String subjectState;

    public String getSubjectState() {
        return subjectState;
    }

    public void setSubjectState(String subjectState) {
        this.subjectState = subjectState;
        // 通知所有观察者
        notifyObservers();
    }
}
