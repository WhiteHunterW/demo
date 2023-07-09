package com.example.springframe.service;

import com.example.springframe.beans.proccessor.BeanPostProcessor;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/7/8
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if("userServiceImpl".equals(beanName)) {
            UserServiceImpl userService = (UserServiceImpl) bean;
            userService.setCompany("广告传媒公司");
            return userService;
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }
}
