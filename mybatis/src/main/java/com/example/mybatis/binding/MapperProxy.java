package com.example.mybatis.binding;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author wenzeng
 * @date 2023/12/17
 */
public class MapperProxy<T> implements InvocationHandler {

    /**
     * 解析代理类中的SQL statement
     */
    private final Map<String, String> sqlSession;

    /**
     * 代理的接口
     */
    private final Class<T> mapperInterface;

    public MapperProxy(Map<String, String> sqlSession, Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
        this.sqlSession = sqlSession;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 传进来的method是那个类的方法？proxy是被代理的对象？
        if(Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        } else {
            return "你的类被代理了" + sqlSession.get(mapperInterface.getName() + "."+ method.getName());
        }
    }
}
