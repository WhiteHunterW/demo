package com.example.biz.data;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author wz
 * @date 2022/1/16
 */
@Data
public class UserInfoVO {

    @NotEmpty(message = "用户名不能为空")
    private String UserName;

    @NotNull(message = "性别不能为空")
    private String gender;

    private String created;

    private String address;

    private String phone;
}
