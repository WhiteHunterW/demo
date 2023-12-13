package com.example.biz.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/8/30
 */
@Configuration
public class kafkaConfig {


    @Value("${kafka.topic.test-topic}")
    private String testTopic;


    @Bean
    public NewTopic createTopic() {
        return new NewTopic(testTopic, 1, (short) 1);
    }

}
