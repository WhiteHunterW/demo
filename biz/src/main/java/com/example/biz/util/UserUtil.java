package com.example.biz.util;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.example.biz.data.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author wenzeng
 * @date 2024/8/16
 */
@Slf4j
@Component
public class UserUtil {
    private static final ThreadLocal<User> userInfo = new ThreadLocal<>();

    private static final InheritableThreadLocal<User> INHERITABLE_THREAD_LOCAL = new InheritableThreadLocal<>();

    private static final TransmittableThreadLocal<User> TRANSMITTABLE_THREAD_LOCAL = new TransmittableThreadLocal<>();


    public void setUserInfo(String name) {
        User user = new User();
        user.setName(name);
        userInfo.set(user);
        INHERITABLE_THREAD_LOCAL.set(user);
        TRANSMITTABLE_THREAD_LOCAL.set(user);
    }

    public User getUserInfo() {
        log.info("user thread {}", Thread.currentThread());
        return userInfo.get();
    }

    public User getInheritableUser() {
        log.info("user thread {}", Thread.currentThread());
        return INHERITABLE_THREAD_LOCAL.get();
    }

    public User getTTLUser() {
        log.info("TTL user thread {}", Thread.currentThread());
        return TRANSMITTABLE_THREAD_LOCAL.get();
    }




}
