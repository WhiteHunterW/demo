package com.example.biz.design.adapter;

import java.util.List;

/**
 * @author wenzeng
 * @date 2024/12/10
 */
public interface LogFileOperateApi {

    /**
     * 读取日志列表
     * @return
     */
    List<LogModel> readFile();

    /**
     * 写日志文件
     * @param logModels
     */
    void writeFile(List<LogModel> logModels);
}
