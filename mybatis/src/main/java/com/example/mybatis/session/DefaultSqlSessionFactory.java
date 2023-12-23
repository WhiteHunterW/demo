package com.example.mybatis.session;

/**
 * SqlSession工厂类 创建SqlSession
 * @author wenzeng
 * @date 2023/12/18
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory{


    @Override
    public SqlSession openSqlSession() {
        return new DefaultSqlSession();
    }
}
