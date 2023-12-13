package com.example.biz.config;

import com.example.biz.listener.retry.DefaultRetrySupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

/**
 * @author wz
 * @date 2022/5/5
 */
@Configuration
public class SpringRetryConfiguration {

    @Bean("retryTemplateBuilding")
    public RetryTemplate retryTemplate(){
        return RetryTemplate.builder()
                .retryOn(NumberFormatException.class)
                .maxAttempts(3)
                .fixedBackoff(100)
                .build();
    }

    @Bean("retryTemplate")
    public RetryTemplate retry(){
        RetryTemplate retryTemplate = new RetryTemplate();
        // 回调监听
        retryTemplate.registerListener(new DefaultRetrySupport());
        // 退避策略
        FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
        fixedBackOffPolicy.setBackOffPeriod(100L);
        retryTemplate.setBackOffPolicy(fixedBackOffPolicy);
        // 重试策略
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(3);
        retryTemplate.setRetryPolicy(retryPolicy);
        return retryTemplate;
    }
}
