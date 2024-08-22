package com.example.biz.data;

import lombok.Data;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/8/4
 */
@Data
public class UserDetail extends User{

    private String address;

    private String phone;

    private String name;

    private int count = 20;

    public int getCount() {
        return super.getCount();
    }
}
