package com.example.mybatis.session;

import com.example.mybatis.builder.xml.XMLConfigBuilder;
import com.example.mybatis.session.defaults.DefaultSqlSessionFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.Reader;

/**
 * @author wenzeng
 * @date 2023/12/23
 */
@Slf4j
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(Reader reader) {
        XMLConfigBuilder builder = new XMLConfigBuilder(reader);
        return null;
    }


    public SqlSessionFactory build(Configuration configuration) {
        return new DefaultSqlSessionFactory(configuration.mapperRegistry);
    }
}
