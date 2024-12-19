package com.example.biz.design.adapter;

import java.util.List;

/**
 * @author wenzeng
 * @date 2024/12/11
 */
public class DBAdapter implements LogFileOperateApi {

    private final LogDBOperatorApi dbOperatorApi;

    public DBAdapter(LogDBOperatorApi api) {
        this.dbOperatorApi = api;
    }

    @Override
    public List<LogModel> readFile() {
        return dbOperatorApi.listAllLog();
    }

    @Override
    public void writeFile(List<LogModel> logModels) {
        dbOperatorApi.create(logModels);
    }
}
