package com.example.demo.config;

import com.example.demo.condition.CheckCondition;
import com.example.demo.condition.CheckConditionTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Function:
 *
 * @author xingche
 * @date 2023/2/22
 */
@Configuration
public class ConditionConfig {

    @CheckConditionTest(name = "var1", value = "var1")
    @Bean
    public CheckCondition checkCondition(){
        return new CheckCondition();
    }

    @CheckConditionTest(name = "var2", value = "var2")
    @Bean
    public CheckCondition checkCondition2(){
        // do somthing 设置不同的配置数据
        return new CheckCondition();
    }
}
