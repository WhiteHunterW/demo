package com.example.mybatis.session;

import com.example.common.BizException;
import com.example.mybatis.binding.MapperRegistry;
import com.example.mybatis.mapping.MappedStatement;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wenzeng
 * @date 2023/12/23
 */
public class Configuration {
    protected MapperRegistry mapperRegistry = new MapperRegistry(this);
    protected Map<String, MappedStatement> mappedStatements = new HashMap<>();


    /**
     * 注册XML中的SQL语句
     * @param ms
     */
    public void addMappedStatement(MappedStatement ms) {
        MappedStatement statement = mappedStatements.get(ms.getId());
        if(null != statement) {
            throw new BizException(ms.getId() + "is repeat");
        }
        mappedStatements.put(ms.getId(), ms);
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
     */
    public void addMappers(String packageName) {
        mapperRegistry.addMappers(packageName);
    }

    /**
     * 获取mapper实例
     * @param type
     * @param sqlSession
     * @return
     * @param <T>
     */
    public <T> T getMapper(Class<T> type, SqlSession sqlSession){
        return mapperRegistry.getMapper(type, sqlSession);
    }

    /**
     * 获取MappedStatement对象
     * @param mappedStatement key
     * @return
     */
    public MappedStatement getMappedStatement(String mappedStatement){
        return mappedStatements.get(mappedStatement);
    }
}
