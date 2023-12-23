package com.example.mybatis.session;

import com.example.mybatis.binding.MapperRegistry;

/**
 * SqlSession工厂类 创建SqlSession
 * @author wenzeng
 * @date 2023/12/18
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory{

    private final MapperRegistry mapperRegistry;

    public DefaultSqlSessionFactory(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public SqlSession openSqlSession() {
        return new DefaultSqlSession(mapperRegistry);
    }
}
