package com.example.demo;

import com.example.springframe.beans.BeanDefinition;
import com.example.springframe.beans.BeanReReference;
import com.example.springframe.beans.PropertyValue;
import com.example.springframe.beans.PropertyValues;
import com.example.springframe.beans.support.DefaultListableBeanFactory;
import com.example.springframe.service.UserService;
import com.example.springframe.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/6/25
 */
@SpringBootTest
public class BeanFactoryTest {


    @Test
    public void testBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinition definition = new BeanDefinition(UserServiceImpl.class);
        beanFactory.registryBeanDefinition("userService", definition);

        // 获取bean
        UserService userService = (UserServiceImpl) beanFactory.getBean("userService");
        userService.insertUser(null);

        UserService userService1 = (UserServiceImpl) beanFactory.getBean("userService");
        userService1.insertUser(null);
    }


    @Test
    public void testParamConstructor() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        BeanDefinition definition = new BeanDefinition(UserServiceImpl.class);
        beanFactory.registryBeanDefinition("userService", definition);

        UserService userService = (UserServiceImpl) beanFactory.getBean("userService", "wenzeng");
        userService.insertUser(null);
    }


    @Test
    void testPropertyValues() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 注册依赖对象
        /*
         * 这里注册的时候没有对RetryService添加属性，这个对象是没有属性对
         * 因此在applyPropertyValues() 方法中要对属性判空
         * 第5章对博客里没有判空 导致在测试对时候发生了NPE
         */
        beanFactory.registryBeanDefinition("retryService", new BeanDefinition(Object.class));

        PropertyValues propertyValues = new PropertyValues();
        PropertyValue userName = new PropertyValue("userName", "wenzeng");
        PropertyValue retryService = new PropertyValue("retryService", new BeanReReference("retryService"));
        propertyValues.addPropertyValue(userName);
        propertyValues.addPropertyValue(retryService);

        // 注册需要实例化的对象
        BeanDefinition definition = new BeanDefinition(UserServiceImpl.class, propertyValues);
        beanFactory.registryBeanDefinition("userService", definition);

        UserService userService = (UserServiceImpl) beanFactory.getBean("userService");
        userService.insertUser(null);
    }

}
