package com.example.biz.config;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author wz
 * @date 2022/4/9
 */
@Configuration
public class EventBusConfig {

    @Bean
    public EventBus eventBus(){
        return new EventBus();
    }

    @Bean("asyncEventBus")
    public AsyncEventBus asyncEventBus(){
        final ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(3,
                new ThreadFactoryBuilder()
                        .setNameFormat("getAsyncEventBus Thread pool %d ")
                        .setDaemon(true).build());
        return new AsyncEventBus(executorService);
    }
}
