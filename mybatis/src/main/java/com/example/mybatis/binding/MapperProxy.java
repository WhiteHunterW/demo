package com.example.mybatis.binding;

import com.example.mybatis.session.SqlSession;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 接口代理类
 * @author wenzeng
 * @date 2023/12/17
 */
@Slf4j
public class MapperProxy<T> implements InvocationHandler {

    /**
     * 解析代理类中的SQL statement
     */
    private Map<String, String> sqlSession2;

    private SqlSession sqlSession;

    /**
     * 代理的接口
     */
    private final Class<T> mapperInterface;

    public MapperProxy(Map<String, String> sqlSession, Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
        this.sqlSession2 = sqlSession;
    }

    /**
     * 实例化代理类
     * 要传SqlSession
     * SqlSession定义了对数据库的操作 执行SQL
     * @param sqlSession
     * @param mapperInterface
     */
    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
        this.sqlSession = sqlSession;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 传进来的method是目标类的方法
       /* log.info("当前类 {}", Object.class);
        log.info("方法所在类 {}", method.getDeclaringClass());*/
        if(Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        } else {
            // 调用目标对象中的方法
            // method.invoke(mapperInterface, args);
            return "你的类被代理了" + sqlSession.getMapper(this.mapperInterface);
        }
    }
}
