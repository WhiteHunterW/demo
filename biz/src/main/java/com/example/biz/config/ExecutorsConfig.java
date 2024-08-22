package com.example.biz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author wenzeng
 * @date 2024/8/15
 */
@Configuration
public class ExecutorsConfig {


    @Bean
    public ThreadPoolExecutor executorService() {
        return new ThreadPoolExecutor(2, 2, 10,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(500));
    }

}
