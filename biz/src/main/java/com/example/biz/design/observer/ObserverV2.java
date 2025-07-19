package com.example.biz.design.observer;

import lombok.extern.slf4j.Slf4j;

import java.util.Observable;
import java.util.Observer;

/**
 * Observer：JDK自带的观察者接口
 * @author wenzeng
 * @date 2025/1/21
 */
@Slf4j
public class ObserverV2 implements Observer {

    /**
     * 观察者名称
     */
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        // 采用推的方式 从arg中获取推的时候传入的参数
        log.info("{}读者接收到信息，内容是：{}", name, arg);
        // 采用拉的方式，从Observable 目标对象中获取数据
        log.info("{}读者拉数据，内容是:{}", name, ((NewsPaperV2)o).getContent());
    }


    public static void main(String[] args) {
        NewsPaperV2 paperV2 = new NewsPaperV2();
        ObserverV2 observer1 = new ObserverV2();
    }
}
