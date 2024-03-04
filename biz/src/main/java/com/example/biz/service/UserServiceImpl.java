package com.example.biz.service;

import com.example.biz.assembler.UserAssembler;
import com.example.biz.data.User;
import com.example.biz.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Function:
 *
 * @author xingche
 * @date 2023/2/16
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    @Resource
    private UserMapper userMapper;

    private RetryService retryService;

    private String userName;

    private final UserAssembler userAssembler = UserAssembler.INSTANCE;

    public UserServiceImpl() {

    }


    public UserServiceImpl(String userName) {
        this.userName = userName;
    }


    /*public UserServiceImpl(@Autowired RetryService retryService) {
        this.retryService = retryService;
    }*/

    @Override
    public void insertUser(User user) {
        /*//UserInfoVO userInfoVO = userAssembler.userToVO(user);
        UserChangeEvent userChangeEvent = new UserChangeEvent();
        userChangeEvent.setUserId(String.valueOf(Math.random()));
        userChangeEvent.setUserName("test");
        userChangeEvent.setOperatorName("test");
        applicationEventPublisher.publishEvent(userChangeEvent);*/
        userMapper.insert(user);
    }

    @Override
    public List<User> selectUser(String userName, Integer count) {
        return userMapper.selectUser(userName, count);
    }

    @Override
    public List<User> selectUserMap(Map<String, Object> paramMap) {
        return userMapper.selectUserMap(paramMap);
    }

    @Override
    public List<User> selectUserParam(String userName, Integer count) {
        return userMapper.selectUserParam(userName, count);
    }
}
