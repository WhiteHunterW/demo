package com.example.mybatis;

import com.example.mybatis.binding.MapperProxyFactory;
import com.example.mybatis.mapper.UserTestMapper;
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
}
