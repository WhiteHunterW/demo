package com.example.mybatis.mapper;

/**
 * @author wenzeng
 * @date 2023/12/17
 */
public interface UserTestMapper {

    /**
     * 查询用户名
     * @param uid
     * @return
     */
    String queryUserName(String uid);

    /**
     * 查询用户年龄
     * @param uid
     * @return
     */
    Integer queryUserAge(String uid);
}
