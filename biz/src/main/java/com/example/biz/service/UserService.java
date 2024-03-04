package com.example.biz.service;


import com.example.biz.data.User;

import java.util.List;
import java.util.Map;

/**
 * Function:
 *
 * @author xingche
 * @date 2023/2/16
 */
public interface UserService {

    /**
     *  插入数据库
     * @param user
     */
    void insertUser(User user);

    /**
     * 查询用户信息 按位置指定参数
     * @param userName
     * @param count
     * @return
     */
    List<User> selectUser(String userName, Integer count);

    /**
     * 查询用户信息
     * @param paramMap 参数map
     * @return
     */
    List<User> selectUserMap(Map<String, Object> paramMap);

    /**
     * 查询用户信息 @param指定参数
     * @param userName
     * @param count
     * @return
     */
    List<User> selectUserParam(String userName,Integer count);

}
