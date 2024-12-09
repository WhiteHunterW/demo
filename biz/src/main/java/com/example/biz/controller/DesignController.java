package com.example.biz.controller;

import com.example.biz.design.CscQuery;
import com.example.biz.design.CsmQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wenzeng
 * @date 2024/11/29
 */
@RequestMapping("/design")
@RestController
public class DesignController {

    @Resource
    private CsmQuery csmQuery;

    @GetMapping("/chain")
    public void testChain() {
        CscQuery query = CscQuery.INSTANCE;
        query.addNextQueryState();
    }
}
