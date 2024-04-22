package com.example.mybatis.session.defaults;

import com.example.mybatis.mapping.MappedStatement;
import com.example.mybatis.session.Configuration;
import com.example.mybatis.session.SqlSession;

/**
 * @author wenzeng
 * @date 2023/12/18
 */
public class DefaultSqlSession implements SqlSession {

    /*private final MapperRegistry mapperRegistry;*/

    private final Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T)"DefaultSqlSession";
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        MappedStatement mappedStatement = configuration.getMappedStatement(statement);
        return null;
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }
}
