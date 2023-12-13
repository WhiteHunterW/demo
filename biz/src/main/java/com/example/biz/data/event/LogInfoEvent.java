package com.example.biz.data.event;

import lombok.Data;

/**
 * @author wz
 * @date 2022/4/9
 */
@Data
public class LogInfoEvent {

    private String data;
    private String logType;
    private String creator;
    private String businessType;
}
