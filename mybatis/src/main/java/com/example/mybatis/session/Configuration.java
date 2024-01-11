package com.example.mybatis.session;

import com.example.common.BizException;
import com.example.mybatis.binding.MapperRegistry;
import com.example.mybatis.mapping.MappedStatement;

import java.util.List;
import java.util.Map;

/**
 * @author wenzeng
 * @date 2023/12/23
 */
public class Configuration {

    protected MapperRegistry mapperRegistry;

    protected Map<String, MappedStatement> mappedStatements;


    /**
     * 注册XML中的SQL语句
     * @param ms
     */
    public void addMappedStatement(MappedStatement ms) {
        MappedStatement statement = mappedStatements.get(ms.getId());
        if(null == statement) {
            throw new BizException(ms.getId() + "is repeat");
        }
        mappedStatements.put(statement.getId(), ms);
    }

    /**
     * 注册dao层接口
     * @param type
     * @param <T>
     */
    public <T> void addMapper(Class<T> type) {
        mapperRegistry.addMapper(type);
    }

    /**
     * 注册整个包下面的mapper
     * @param packageName
     * @param <T>
     */
    public <T> void addMappers(String packageName) {
        mapperRegistry.addMappers(packageName);
    }
}
