package com.example.demo.service;

import com.example.demo.assembler.UserAssembler;
import com.example.demo.data.User;
import com.example.demo.event.UserChangeEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

    private RetryService retryService;

    private String userName;

    private UserAssembler userAssembler = UserAssembler.INSTANCE;

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
        //UserInfoVO userInfoVO = userAssembler.userToVO(user);
        UserChangeEvent userChangeEvent = new UserChangeEvent();
        userChangeEvent.setUserId(String.valueOf(Math.random()));
        userChangeEvent.setUserName("test");
        userChangeEvent.setOperatorName("test");
        applicationEventPublisher.publishEvent(userChangeEvent);
    }
}
