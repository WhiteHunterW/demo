package com.example.biz.aop;

import com.example.biz.service.UserService;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Function:
 *
 * @author xingche
 * @date 2023/2/16
 */
@Slf4j
public class UserServiceJDKProxy implements InvocationHandler {

    private UserService userService;

    public UserServiceJDKProxy(UserService userService){
        this.userService = userService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(userService, args);
        after();
        return result;
    }


    public void before(){
        System.out.println(this + "前置增强");
    }

    public void after(){
        System.out.println(this + "后置增强");
    }
}
