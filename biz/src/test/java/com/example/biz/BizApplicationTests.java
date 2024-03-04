package com.example.biz;

import com.alibaba.fastjson.JSON;
import com.example.biz.data.User;
import com.example.biz.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.input.ReaderInputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootTest
class BizApplicationTests {

    @Test
    void contextLoads() throws IOException {
        String url = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(url);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setName("wenzeng");
        user.setCount(1);
        //userMapper.insert(user);

        List<User> userList = userMapper.selectUser("wenz", 1);
        List<User> userParam = userMapper.selectUserParam("wenz", 1);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userName", "wenz");
        paramMap.put("count", 1);
        List<User> userMap = userMapper.selectUserMap(paramMap);
        log.info("userList {}", JSON.toJSON(userList));
        log.info("userParam {}", JSON.toJSON(userParam));
        log.info("userMap {}", JSON.toJSON(userMap));
    }

}
