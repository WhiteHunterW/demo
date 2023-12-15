package com.example.biz.mapper;


import com.example.biz.data.User;
import org.apache.ibatis.annotations.Param;

/**
 * Function:
 *
 * @author xingche
 * @date 2023/2/20
 */
public interface UserMapper {

    int insert(@Param("user") User user);
}
