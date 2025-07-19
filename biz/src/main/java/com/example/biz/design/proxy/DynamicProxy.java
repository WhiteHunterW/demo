package com.example.biz.design.proxy;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 订单动态代理
 * 动态代理对比静态代理的优点：
 * 动态代理利用Java反射，只需要重写invoke()方法，不需要关心被代理类的扩展/改动
 * 静态代理（示例的UserProxy，跟被代理类实现了相同的接口，如果接口需要新增/删除方法，代理类也需要改
 *
 * 静态代理：代理类和被代理类可以不实现共用接口，代理类直接继承被代理类
 *
 * 区分代理模式，适配器模式和装饰模式
 * @author wenzeng
 * @date 2025/1/20
 */
@Slf4j
public class DynamicProxy implements InvocationHandler {

    private OrderApi orderApi;

    /**
     * 获取实例
     * @param order
     * @return
     */
    public OrderApi getProxyInterface(Order order) {
        this.orderApi = order;
        OrderApi orderApi1 = (OrderApi) Proxy.newProxyInstance(order.getClass().getClassLoader(),
                order.getClass().getInterfaces(), this);
        return orderApi1;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 调用set方法需要检查用户权限
        if(method.getName().startsWith("set")) {
            if(StringUtils.isNotEmpty(orderApi.getOrderUser()) && args[1].equals(orderApi.getOrderUser())) {
                // 实际调用的是OrderApi里的方法
                return method.invoke(orderApi, args);
            } else {
                log.error("当前用户:{}无权限修改订单数据", args[1]);
            }
        } else {
            // 查询方法
            return method.invoke(orderApi, args);
        }
        return null;
    }

}
