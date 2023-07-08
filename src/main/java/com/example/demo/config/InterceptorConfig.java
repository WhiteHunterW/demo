package com.example.demo.config;

import com.example.demo.interceptor.UserAccessInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Function:
 * 将拦截器注册到WebMvcConfigurer
 * @author wenzeng
 * @date 2023/4/25
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private UserAccessInterceptor accessInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessInterceptor);
    }
}
