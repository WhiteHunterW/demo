package com.example.biz.design.observer;

/**
 * @author wenzeng
 * @date 2025/1/21
 */
public class NewsPaper extends Subject{

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
        // 当报纸有新内容的时候 通知所有订阅者
        notifyObservers();
    }
}
