package com.example.demo;

import com.example.demo.service.UserService;
import com.example.demo.service.UserServiceImpl;
import com.example.springframe.bean.BeanDefinition;
import com.example.springframe.support.DefaultListableBeanFactory;
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

}
