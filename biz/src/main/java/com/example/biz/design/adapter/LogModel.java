package com.example.biz.design.adapter;

import lombok.Data;

import java.io.Serializable;

/**
 * 日志记录模版对象
 * @author wenzeng
 * @date 2024/12/10
 */
@Data
public class LogModel implements Serializable {

    private static final long serialVersionUID = -532627294034194338L;
    /**
     * 日志ID
     */
    private String logId;
    /**
     * 操作人
     */
    private String operator;

    /**
     * 记录时间
     */
    public String operateTime;

    /**
     * 日志内容
     */
    private String logContent;

}
