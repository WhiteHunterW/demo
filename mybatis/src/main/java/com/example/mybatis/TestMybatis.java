package com.example.mybatis;

import com.example.mybatis.binding.MapperProxyFactory;
import com.example.mybatis.binding.MapperRegistry;
import com.example.mybatis.mapper.RoleMapper;
import com.example.mybatis.mapper.UserTestMapper;
import com.example.mybatis.session.DefaultSqlSessionFactory;
import com.example.mybatis.session.SqlSession;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;

/**
 * @author wenzeng
 * @date 2023/12/17
 */
@Slf4j
public class TestMybatis {

    @Test
    public void testProxy() {
        MapperProxyFactory<UserTestMapper> factory = new MapperProxyFactory<>(UserTestMapper.class);
        HashMap<String, String> sqlSession = new HashMap<>(2);
        sqlSession.put("com.example.mybatis.mapper.UserTestMapper.queryUserName", "select name from user where uid = #{uid}");
        UserTestMapper userTestMapper = factory.newInstance(sqlSession);
        String nameTest = userTestMapper.queryUserName("2");
        log.info("名称查询测试 {}", nameTest);
    }

    /**
     * 测试 mapper映射器注册和使用
     */
    @Test
    public void test() {
        // 注册
        MapperRegistry registry = new MapperRegistry();
        registry.addMappers("com.example.mybatis.mapper");
        // 实例化SqlSession
        DefaultSqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(registry);
        SqlSession sqlSession = sqlSessionFactory.openSqlSession();
        // 获取接口的代理类对象 执行接口方法
        UserTestMapper testMapper = registry.getMapper(UserTestMapper.class, sqlSession);
        RoleMapper roleMapper = registry.getMapper(RoleMapper.class, sqlSession);
        log.info("testMapper {}", testMapper.queryUserName("22"));
        log.info("roleMapper {}", roleMapper.getUserRole("22"));
    }

    @Test
    public void proxy() {

    }
}
