package com.example.biz.helper;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author wenzeng
 * @date 2024/12/3
 */
@Component
public class ApplicationContextHelper {

    private static ApplicationContext applicationContext;


    public <T> T getBean(Class<T> tClass) {
        return applicationContext.getBean(tClass);
    }



}
