package com.example.demo.service;

import com.example.demo.data.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Function:
 *
 * @author xingche
 * @date 2023/2/16
 */
@Slf4j
@Service
public class UserServiceImpl {

    /*@Autowired
    private UserMapper userMapper;*/

    public void insertUser(User user) {
        if(Objects.isNull(user)){
            return;
        }
        //userMapper.insert(user);
    }
}
