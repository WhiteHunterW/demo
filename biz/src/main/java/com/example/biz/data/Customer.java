package com.example.biz.data;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author wenzeng
 * @date 2024/11/29
 */
@Data
public class Customer {

    /**
     * 客户编码
     */
    private String customerNo;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 成交时间
     */
    private LocalDateTime dealTime;

    /**
     * 销售人员
     */
    private String salesUser;

    /**
     * csc
     */
    private String csc;

    /**
     * csm
     */
    private String csm;

    /**
     * 到期时间
     */
    private LocalDateTime expireTime;
}
