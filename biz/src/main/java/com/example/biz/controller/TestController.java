package com.example.biz.controller;

import com.alibaba.fastjson.JSON;
import com.example.biz.data.User;
import com.example.biz.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

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
        user.setName("test");
        user.setGender(1);
        user.setCreated(new Date());
        user.setCount(1);
        log.info("date {}", user.getCreated().getTime());
        userService.insertUser(user);
    }
}
