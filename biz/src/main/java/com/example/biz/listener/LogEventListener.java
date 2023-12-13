package com.example.biz.listener;

import com.example.biz.data.event.LogInfoEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;


/**
 * @author wz
 * @date 2022/4/9
 */
@Component
@Slf4j
public class LogEventListener {

    @Resource
    private EventBus eventBus;

    public static final String STRING = "rwer";

    @PostConstruct
    public void init(){
        eventBus.register(this);
    }

    @Subscribe
    public void logEventListener(LogInfoEvent event) {
        log.info("我是执行方法logEventListener {}, {}", System.currentTimeMillis(), Thread.currentThread().getName());
        log.info("result1 {}",event);
    }

    @Subscribe
    protected void logEventListener2(LogInfoEvent event) {
        log.info("我是执行方法logEventListener2 {}, {}", System.currentTimeMillis(), Thread.currentThread().getName());
        log.info("result2 {}",event);
    }

    public Object test() {
        return null;
    }

    @Subscribe
    public void logEventListener3(LogInfoEvent event) {
        log.info("我是执行方法logEventListener3 {}, {} ", System.currentTimeMillis(), Thread.currentThread().getName());
        log.info("result3 {}", event);
    }


}
