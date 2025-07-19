package com.example.biz.design.observer;

import lombok.extern.slf4j.Slf4j;

/**
 * 一个观察者可以观察多个目标对象，可以实现多个观察者接口？或者一个观察者接口包含多个业务对象修改的方法？
 * @author wenzeng
 * @date 2025/1/21
 */
@Slf4j
public class Reader implements Observer{


    /**
     * 读者信息
     */
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void update(Subject subject) {
        log.info("读者:{}收到报纸信息,内容是:{}", name, ((NewsPaper) subject).getContent());
    }

    public static void main(String[] args) {
        // 生成一份报纸对象：有订阅者，有内容
        NewsPaper paper = new NewsPaper();
        Reader reader = new Reader();
        reader.setName("读者1");
        Reader reader1 = new Reader();
        reader1.setName("读者2");
        // 添加报纸的订阅者
        paper.attach(reader);
        paper.attach(reader1);
        // 更新报纸的内容
        paper.setContent("报纸报纸报纸报纸");
    }


}
