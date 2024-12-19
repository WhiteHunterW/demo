package com.example.biz.design.adapter;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wenzeng
 * @date 2024/12/11
 */
@Slf4j
@Component
public class LogDBOperate implements LogDBOperatorApi{

    public static final List<LogModel> LOGS = new ArrayList<>();

    @Override
    public void create(List<LogModel> logModels) {
        log.info("DB create -- 新增日志, {}", JSON.toJSONString(logModels));
        LOGS.addAll(logModels);
    }

    @Override
    public void remove(String logId) {
        log.info("DB remove -- 删除日志");
        LOGS.removeIf(logModel -> logModel.getLogId().equals(logId));
    }

    @Override
    public List<LogModel> listAllLog() {
        log.info("DB listAllLog -- 查看所有日志");
        return LOGS;
    }
}
