package com.example.demo.kafka;

import com.example.common.BizException;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.Serializable;
import java.util.Properties;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/8/19
 */
public class CustomerProducer {

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        // 连接集群
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "111.230.46.50:2181");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, Serializable.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, Serializable.class.getName());
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 100);
        properties.put(ProducerConfig.LINGER_MS_CONFIG, 100);
        properties.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "transactionId");
        // 指定key value
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        // 初始化事务 写在这儿？？
        producer.initTransactions();
        // 开启事务
        producer.beginTransaction();
        try {
            producer.send(new ProducerRecord<>("test","testmessage"), (metadata, exception) -> {
                if(exception == null) {
                    System.out.println(metadata.topic() +  metadata.partition());
                } else {
                    System.out.println("send message error");
                }
            });
            // 提交事务
            producer.commitTransaction();
        } catch (Exception e) {
            // 回滚事务
            producer.abortTransaction();
            throw new BizException("error message");
        }
    }
}
