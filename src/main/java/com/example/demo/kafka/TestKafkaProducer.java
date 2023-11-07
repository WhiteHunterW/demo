package com.example.demo.kafka;

import com.alibaba.fastjson.JSON;
import com.example.demo.data.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/8/31
 */
@Slf4j
@Component
public class TestKafkaProducer {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic.test-topic}")
    private String testTopic;

    /**
     * 发送kafka消息
     * @param user
     */
    public void setTestMessage(User user) {
        kafkaTemplate.send(testTopic, JSON.toJSONString(user));
    }

}
