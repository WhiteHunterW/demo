package com.example.mybatis.builder;

import com.example.mybatis.session.Configuration;

/**
 * @author wenzeng
 * @date 2023/12/23
 */
public class BaseBuilder {

    protected Configuration configuration;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
    }
}
