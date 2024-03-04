package com.example.biz.mapper;


import com.example.biz.data.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Function:
 *
 * @author xingche
 * @date 2023/2/20
 */
public interface UserMapper {

    int insert(User user);

    List<User> selectUser(String userName, Integer count);

    List<User> selectUserMap(Map<String, Object> paramMap);

    List<User> selectUserParam(@Param("userName") String userName, @Param("count") Integer count);

}
