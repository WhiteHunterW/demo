package com.example.demo.listener;

import com.example.demo.data.event.LogInfoEvent;
import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xingche
 * @description
 * @date 2022/7/17
 */
@Slf4j
public class LogTest extends LogEventListener{


    @Override
    @Subscribe
    public void logEventListener2(LogInfoEvent event) {
        log.info("我是执行方法logEventListener2 {}, {}", System.currentTimeMillis(), Thread.currentThread().getName());
        log.info("result2 {}",event);
    }

    @Override
    public String test() {
        short s1 = 1;
        s1 = (short) (s1 + 1);
        String str = LogTest.STRING;
        return null;
    }
}
