package com.example.biz.design.adapter;

import com.example.biz.practice.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wenzeng
 * @date 2024/12/10
 */
@Slf4j
@Component
public class LogFileOperate implements LogFileOperateApi{

    @Value("${log.file.name}")
    private String logFileName;



    @Override
    public List<LogModel> readFile() {
        return FileUtils.readerBuffer(logFileName, LogModel.class);
    }

    @Override
    public void writeFile(List<LogModel> logModels) {
        FileUtils.writerBuffer(logModels, logFileName);
    }
}
