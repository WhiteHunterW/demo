package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Function:
 *
 * @author xingche
 * @date 2023/3/12
 */
@Configuration
public class ConfigBean {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
