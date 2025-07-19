package com.example.biz.design.observer;

import java.util.Observable;

/**
 * JDK自带的观察者模式实现
 * Observable：JDK自带的目标对象，包含对观察者的操作：添加/删除/通知
 * @author wenzeng
 * @date 2025/1/21
 */
public class NewsPaperV2 extends Observable {

    /**
     * 报纸内容
     */
    private String content;

    public String getContent() {
        return content;
    }

    /**
     * 设置报纸内容
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
        // 1.目标对象的状态修改，必须要调用
        this.setChanged();
        // 2.通知观察者(主动通知，推) ; 拉：notifyObservers()，不传参，让观察者再次获取目标对象
        notifyObservers(content);
    }
}
