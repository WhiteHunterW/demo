package com.example.mybatis.session.defaults;

import com.example.mybatis.binding.MapperRegistry;
import com.example.mybatis.session.SqlSession;
import com.example.mybatis.session.SqlSessionFactory;

/**
 * SqlSession工厂类 创建SqlSession
 * @author wenzeng
 * @date 2023/12/18
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final MapperRegistry mapperRegistry;

    public DefaultSqlSessionFactory(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public SqlSession openSqlSession() {
        return new DefaultSqlSession(mapperRegistry);
    }
}
