package com.example.biz.event;

import lombok.Data;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/10/28
 */
@Data
public class UserChangeEvent {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 操作人
     */
    private String operatorName;
}
