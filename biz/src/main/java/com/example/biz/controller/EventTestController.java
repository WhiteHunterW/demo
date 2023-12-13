package com.example.biz.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.biz.data.UserInfoVO;
import com.example.biz.data.event.LogInfoEvent;
import com.example.biz.transfer.ConvertUtil;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wz
 * @date 2022/4/9
 */
@RestController
@Slf4j
public class EventTestController {

    @Resource
    private EventBus eventBus;

    @Resource
    private AsyncEventBus asyncEventBus;

    @PostMapping("/save/test")
    public void saveBusinessData(@RequestBody UserInfoVO userInfoVO){
        if(Objects.isNull(userInfoVO)){
            return;
        }
        final String data = JSONObject.toJSONString(userInfoVO);
        final LogInfoEvent event = ConvertUtil.convert(userInfoVO, LogInfoEvent.class, (f, t) -> {
            t.setData(data);
            t.setBusinessType("USER_INFO");
            t.setLogType("ADD");
            t.setCreator("test");
        });
        eventBus.post(event);
        log.info("我是调用方法saveBusinessData {}, {}", System.currentTimeMillis(),Thread.currentThread().getName());
        log.info("我是调用方法saveBusinessData  {}", Thread.currentThread().getName());
    }

    @PostMapping("/save/test/sync-event")
    public void saveBusinessDataSync(@RequestBody UserInfoVO userInfoVO){
        if(Objects.isNull(userInfoVO)){
            return;
        }
        final String data = JSONObject.toJSONString(userInfoVO);
        final LogInfoEvent event = ConvertUtil.convert(userInfoVO, LogInfoEvent.class, (f, t) -> {
            t.setData(data);
            t.setBusinessType("USER_INFO");
            t.setLogType("ADD");
            t.setCreator("test");
        });
        //new Thread(() -> asyncEventBus.post(event)).start();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(() -> asyncEventBus.post(event));
        log.info("我是调用方法saveBusinessDataSync {},{}",System.currentTimeMillis() , Thread.currentThread().getName());
        log.info("我是调用方法saveBusinessDataSync {}" , Thread.currentThread().getName());
    }
}
