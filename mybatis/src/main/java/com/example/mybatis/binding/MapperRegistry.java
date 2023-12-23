package com.example.mybatis.binding;

import cn.hutool.core.lang.ClassScanner;
import com.example.common.BizException;
import com.example.mybatis.session.SqlSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * mapper注册器
 * @author wenzeng
 * @date 2023/12/18
 */
public class MapperRegistry {

    private final Map<Class<?>, MapperProxyFactory<?>> knownsMapper = new HashMap<>();


    /**
     * 获取mapper的实例
     * 从map中取出代理工厂，调用代理工厂类中的newInstance方法生成接口实例
     * @param type
     * @return
     * @param <T>
     */
    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>)knownsMapper.get(type);
        if(mapperProxyFactory == null) {
            throw new BizException("type: " + type + "don't registered");
        }
        return mapperProxyFactory.newInstance(sqlSession);
    }


    /**
     * 解析配置路径 获取所有的mapper
     * @param packageName
     */
    public void addMappers(String packageName) {
        Set<Class<?>> classSet = ClassScanner.scanPackage(packageName);
        for (Class<?> clt: classSet) {
            addMapper(clt);
        }
    }

    public <T> void addMapper(Class<T> type) {
        if(!type.isInterface()) {
            throw new BizException("type:" + type + "is not interface");
        }
        MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownsMapper.get(type);
        if(mapperProxyFactory == null) {
            knownsMapper.put(type, new MapperProxyFactory<>(type));
        } else {
            throw new BizException("type: " + type + "is already existed in proxyFactoryMap");
        }
    }

}
