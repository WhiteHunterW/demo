package com.example.mybatis.mapping;

import com.example.mybatis.session.Configuration;

/**
 * @author wenzeng
 * @date 2023/12/23
 */
public class MappedStatement {

    private String resource;

    private Configuration configuration;

    private String id;


    public String getResource() {
        return resource;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public String getId() {
        return id;
    }
}
