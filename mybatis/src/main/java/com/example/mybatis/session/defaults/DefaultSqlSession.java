package com.example.mybatis.session.defaults;

import com.example.mybatis.binding.MapperRegistry;
import com.example.mybatis.session.SqlSession;

/**
 * @author wenzeng
 * @date 2023/12/18
 */
public class DefaultSqlSession implements SqlSession {

    private final MapperRegistry mapperRegistry;

    public DefaultSqlSession(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T)"DefaultSqlSession";
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        return null;
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return mapperRegistry.getMapper(type, this);
    }
}
