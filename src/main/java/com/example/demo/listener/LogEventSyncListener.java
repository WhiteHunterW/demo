package com.example.demo.listener;

import com.example.demo.data.event.LogInfoEvent;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author xingche
 * @date 2022/4/9
 */
@Component
@Slf4j
public class LogEventSyncListener {

    @Resource
    private AsyncEventBus asyncEventBus;

    @PostConstruct
    public void init(){
        asyncEventBus.register(this);
    }

    @Subscribe
    public void logEventListenerAsync(LogInfoEvent event){
        log.info("我是执行方法logEventListenerAsync1  {}, {}", System.currentTimeMillis() , Thread.currentThread().getName());
        log.info("Async1 {}", event);
    }

    @Subscribe
    public void logEventListenerAsync2(LogInfoEvent event){
        log.info("我是执行方法logEventListenerAsync2  {}, {}",  System.currentTimeMillis(), Thread.currentThread().getName());
        log.info("Async2 {}",event);
    }

    @Subscribe
    public void logEventListenerAsync3(LogInfoEvent event){
        log.info("我是执行方法logEventListenerAsync3  {}, {}", System.currentTimeMillis() , Thread.currentThread().getName());
        log.info("Async3 {}", event);
    }

    @Subscribe
    public void logEventListenerAsync4(LogInfoEvent event){
        log.info("我是执行方法logEventListenerAsync4  {}, {}",  System.currentTimeMillis() , Thread.currentThread().getName());
        log.info("Async4 {}", event);
    }

    @Subscribe
    public void logEventListenerAsync5(LogInfoEvent event){
        log.info("我是执行方法logEventListenerAsync5  {}, {}",  System.currentTimeMillis() ,Thread.currentThread().getName());
        log.info("Async5 {}", event);
    }

    @Subscribe
    public void logEventListenerAsync6(LogInfoEvent event){
        log.info("我是执行方法logEventListenerAsync6  {}, {}", System.currentTimeMillis() ,Thread.currentThread().getName());
        log.info("Async6 {}", event);
    }
}
