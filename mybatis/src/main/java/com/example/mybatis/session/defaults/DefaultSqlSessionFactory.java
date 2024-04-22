package com.example.mybatis.session.defaults;

import com.example.mybatis.session.Configuration;
import com.example.mybatis.session.SqlSession;
import com.example.mybatis.session.SqlSessionFactory;

/**
 * SqlSession工厂类 创建SqlSession
 * @author wenzeng
 * @date 2023/12/18
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSqlSession() {
        return new DefaultSqlSession(configuration);
    }
}
