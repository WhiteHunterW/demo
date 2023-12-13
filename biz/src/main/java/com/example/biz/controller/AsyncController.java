package com.example.biz.controller;

import com.example.biz.aop.LogTest;
import com.example.biz.aop.UserServiceCGlibProxy;
import com.example.biz.data.User;
import com.example.biz.service.UserServiceImpl;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * @author wz
 * @date 2022/4/9
 */
@RequestMapping
@RestController
public class AsyncController {

    @Resource
    private UserServiceImpl userService;

    @GetMapping("/test")
    public void syncTest(){
        System.out.println("enter sync test");
    }

    @GetMapping("/test2")
    @LogTest(value = "test2")
    public void test2(){
        System.out.println("测试切面");
    }

    @GetMapping("/test/jdk")
    public void testJDKProxy(){
        /*UserService proxyInstance = (UserService) Proxy.newProxyInstance(userService.getClass().getClassLoader(),
                userService.getClass().getInterfaces(),
                new UserServiceJDKProxy(userService));
        User user = new User();
        user.setName("xingche");
        user.setGender(1);
        proxyInstance.insertUser(user);*/
    }

    @GetMapping("/test/cglib")
    public void testCGLIB() {
        Enhancer enhancer = new Enhancer();
        UserServiceCGlibProxy proxy = new UserServiceCGlibProxy();
        enhancer.setSuperclass(UserServiceImpl.class);
        enhancer.setCallback(proxy);
        UserServiceImpl impl = (UserServiceImpl) enhancer.create();
        User user = new User();
        user.setName("xingche");
        user.setGender(1);
        impl.insertUser(user);
    }

    @GetMapping("/check/test")
    public void checkCondition(){
    }

    @GetMapping("/test/insert")
    public void testMyBatis(){
        User user = new User();
        user.setGender(1);
        user.setName("xingche");
        user.setCreated(new Date());
        userService.insertUser(user);
    }

    public static void main(String[] args) {
        Integer i = 1677254400;
        LocalDate localDate = Instant.ofEpochSecond(i).atZone(ZoneOffset.of("+8")).toLocalDate();
    }
}
