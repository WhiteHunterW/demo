package com.example.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Function:
 *
 * @author xingche
 * @date 2023/2/16
 */
@Slf4j
public class UserServiceCGlibProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object result = methodProxy.invokeSuper(o, objects);
        after();
        return result;
    }

    public void before(){
        System.out.println(this + "cglib前置增强");
    }

    public void after(){
        System.out.println(this + "cglib后置增强");
    }
}
