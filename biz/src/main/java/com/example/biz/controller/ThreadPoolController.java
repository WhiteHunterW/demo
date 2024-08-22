package com.example.biz.controller;

import com.alibaba.ttl.TtlRunnable;
import com.alibaba.ttl.threadpool.TtlExecutors;
import com.example.biz.data.User;
import com.example.biz.util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;

/**
 * @author wenzeng
 * @date 2024/8/16
 */
@Slf4j
@RestController
@RequestMapping
public class ThreadPoolController {


    @Resource
    private ExecutorService executorService;

    @Resource
    private UserUtil userUtil;

    /**
     * 多线程下，线程间信息传递
     */
    @GetMapping("/test/threadpool")
    public void testExecutor(@RequestParam("userName") String userName) {
        // 普通变量
        User user = new User();
        user.setName("test1");
        // ThreadLocal
        userUtil.setUserInfo(userName);
        User testUser2 = userUtil.getUserInfo();
       /* executorService.execute(() -> logUserInfo(user));
        executorService.execute(() -> logUserInfo(testUser2));*/
        executorService.execute(() -> logUserV2(userName));
        executorService.execute(this::logUserV3);
        // 修饰线程池
        TtlExecutors.getTtlExecutor(executorService).execute(() -> logUserV4(userName));
        // 修饰单个任务
        executorService.execute(TtlRunnable.get(() -> logUserV4(userName)));

    }

    private void logUserInfo(User user) {
        log.info("当前线程名称: {}, 用户名 {}", Thread.currentThread(),  user.getName());
    }

    private void logUserV2(String userName) {
        User user = userUtil.getInheritableUser();
        log.info("当前线程名称: {}, 用户名: {}, 传入用户名:{}", Thread.currentThread(), user.getName(), userName);
        Assert.isTrue(userName.equals(user.getName()), "用户名与传入的用户名不相等");
    }

    private void logUserV3() {
        int count = 0;
        for (int i = 0; i < 100; i++) {
            count += i;
        }
        User user = userUtil.getInheritableUser();
        log.info("当前线程名称2: {}, 用户名: {}, count {}", Thread.currentThread(), user.getName(), count);
    }

    private void logUserV4(String userName) {
        log.info("当前线程名称3： {}, TTL用户名 {}, 传入用户名 {}", Thread.currentThread(), userUtil.getTTLUser().getName(), userName);
    }

}
