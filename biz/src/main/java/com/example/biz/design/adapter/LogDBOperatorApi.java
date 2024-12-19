package com.example.biz.design.adapter;

import java.util.List;

/**
 * @author wenzeng
 * @date 2024/12/11
 */
public interface LogDBOperatorApi {


    /**
     * 新增日志
     * @param logModel
     */
    void create(List<LogModel> logModel);

    /**
     * 删除日志
     * @param logId
     */
    void remove(String logId);

    /**
     * 查询日志列表
     * @return
     */
    List<LogModel> listAllLog();



}
