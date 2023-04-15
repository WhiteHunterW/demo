package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Function:
 *
 * @author xingche
 * @date 2023/3/12
 */
@RequestMapping
@RestController
public class EurekaController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/get/ribbon/test")
    public void getStr(){
        restTemplate.getForObject("", Object.class);
        restTemplate.getForEntity("", Object.class, "");
    }
}
