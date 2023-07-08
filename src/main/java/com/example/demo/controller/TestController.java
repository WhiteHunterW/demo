package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/5/17
 */
@RequestMapping
@RestController
public class TestController {


    @PostMapping("/test/param")
    public void testParam(@RequestBody Object param) {
        System.out.println(JSON.toJSON(param));
    }
}
