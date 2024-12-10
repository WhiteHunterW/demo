package com.example.biz.controller;

import com.example.biz.design.adapter.LogFileOperateApi;
import com.example.biz.design.adapter.LogModel;
import com.example.biz.design.state.AbstractQueryState;
import com.example.biz.design.state.CscQuery;
import com.example.biz.design.state.DataAccess;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author wenzeng
 * @date 2024/11/29
 */
@RequestMapping("/design")
@RestController
public class DesignController {

    @Resource
    private LogFileOperateApi operateApi;

    @GetMapping("/chain")
    public void testChain() {
        // query()方法中手动设置下一个执行器，并调用下一个执行器的query方法
        CscQuery query = CscQuery.INSTANCE;
        query.addNextQueryState();
    }

    @GetMapping("/chain1")
    public void testChain1() {
        // 外部循环调用 + 构造函数设置功能链，query逻辑中不用手动调用下一个执行器
        AbstractQueryState queryState = CscQuery.INSTANCE;
        DataAccess access = new DataAccess();
        while(access.isHasData() && Objects.nonNull(queryState)) {
            queryState.query(access);
        }
    }

    @GetMapping("/read/file")
    public List<LogModel> readFile() {
        return operateApi.readFile();
    }

    @PostMapping("/write/file")
    public void testWriteFile(@RequestBody List<LogModel> logModels){
        operateApi.writeFile(logModels);
    }
}
