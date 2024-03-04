package com.example.biz.controller;

import com.alibaba.fastjson.JSON;
import com.example.biz.data.User;
import com.example.biz.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/5/17
 */
@Slf4j
@RequestMapping
@RestController
public class TestController {

    @Resource
    private UserService userService;

    @PostMapping("/test/param")
    public void testParam(@RequestBody Object param) {
        System.out.println(JSON.toJSON(param));
    }

    @PostMapping("/test/insert/user")
    public void testInsert() {
        User user = new User();
        user.setName("test3");
        user.setGender(1);
        user.setCreated(new Date());
        user.setCount(1);
        log.info("date {}", user.getCreated().getTime());
        userService.insertUser(user);
    }

    @GetMapping("/test/mybatis/param")
    public void selectUser() {
        List<User> userList = userService.selectUser("test", 1);
        List<User> userParam = userService.selectUserParam("test", 1);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userName", "test");
        paramMap.put("count", 1);
        List<User> userMap = userService.selectUserMap(paramMap);
        log.info("userList {}", JSON.toJSON(userList));
        log.info("userParam {}", JSON.toJSON(userParam));
        log.info("userMap {}", JSON.toJSON(userMap));
    }
}
