package com.example.biz.controller;

import com.example.biz.design.adapter.DBAdapter;
import com.example.biz.design.adapter.LogDBOperatorApi;
import com.example.biz.design.adapter.LogFileOperateApi;
import com.example.biz.design.adapter.LogModel;
import com.example.biz.design.state.AbstractQueryState;
import com.example.biz.design.state.CscQuery;
import com.example.biz.design.state.DataAccess;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Resource
    private LogDBOperatorApi dbOperatorApi;

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

    /**
     * 场景1: 前端调用不变，业务逻辑从操作日志文件改成操作数据库日志
     * 在拿不到源码的情况下，利用原来已有的功能逻辑，增加适配器转化成新的逻辑
     * @return
     */
    @GetMapping("/read/file")
    public List<LogModel> readFile() {
        //return operateApi.readFile();
        DBAdapter adapter = new DBAdapter(dbOperatorApi);
        return adapter.readFile();
    }

    @PostMapping("/write/file")
    public void testWriteFile(@RequestBody List<LogModel> logModels){
        operateApi.writeFile(logModels);
    }

    /**
     * 读取数据库日志文件
     * @return
     */
    @GetMapping("/read/log/v2")
    public List<LogModel> readLogV2() {
        return dbOperatorApi.listAllLog();
    }

    /**
     * 日志文件写数据库
     * @param logModels
     */
    @PostMapping("/write/log/v2")
    public void writeLogV2(@RequestBody List<LogModel> logModels) {
        dbOperatorApi.create(logModels);
    }

    /**
     * 删除数据库日志
     * @param logId
     */
    @DeleteMapping("/remove/{logId}")
    public void removeLogV2(@PathVariable("logId") String logId) {
        dbOperatorApi.remove(logId);
    }
}
