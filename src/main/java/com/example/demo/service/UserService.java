package com.example.demo.service;

import com.example.demo.data.User;

/**
 * Function:
 *
 * @author xingche
 * @date 2023/2/16
 */
public interface UserService {

    void insertUser(User user);

    default void tw() {
        final int a = 9;

    }

}
