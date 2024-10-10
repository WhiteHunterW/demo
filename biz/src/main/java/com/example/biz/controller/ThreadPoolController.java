package com.example.biz.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.ttl.TtlRunnable;
import com.alibaba.ttl.threadpool.TtlExecutors;
import com.example.biz.data.DataAliPageDTO;
import com.example.biz.data.PublicationRateIndustryResponseDTO;
import com.example.biz.data.User;
import com.example.biz.util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        /*executorService.execute(() -> logUserV2(userName));
        executorService.execute(this::logUserV3);*/
        // 新建匿名类 线程执行
        new Thread(this::logUserV3).start();
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

    public static void main(String[] args) {
        String dataJsonStr = "{\n" +
                "    \"totalNum\": 153,\n" +
                "    \"pageSize\": 300,\n" +
                "    \"rows\": [\n" +
                "        {\n" +
                "            \"jWeekId\": \"2022014\",\n" +
                "            \"productLine\": \"A0\",\n" +
                "            \"productLineDesc\": \"智能屏\",\n" +
                "            \"productLineSub\": \"0000\",\n" +
                "            \"productLineSubDesc\": \"全部\",\n" +
                "            \"industryCode\": \"HY001\",\n" +
                "            \"industryName\": \"大健康\",\n" +
                "            \"industryLevel\": \"1\",\n" +
                "            \"xjRateFz\": \"8640985500.0\",\n" +
                "            \"allRateFz\": \"14741484000.0\",\n" +
                "            \"skFenmu\": \"298693755000.0\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"jWeekId\": \"2022015\",\n" +
                "            \"productLine\": \"A0\",\n" +
                "            \"productLineDesc\": \"智能屏\",\n" +
                "            \"productLineSub\": \"0000\",\n" +
                "            \"productLineSubDesc\": \"全部\",\n" +
                "            \"industryCode\": \"HY001\",\n" +
                "            \"industryName\": \"大健康\",\n" +
                "            \"industryLevel\": \"1\",\n" +
                "            \"xjRateFz\": \"7163353500.0\",\n" +
                "            \"allRateFz\": \"13750071000.0\",\n" +
                "            \"skFenmu\": \"293361606000.0\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"jWeekId\": \"2022021\",\n" +
                "            \"productLine\": \"A0\",\n" +
                "            \"productLineDesc\": \"智能屏\",\n" +
                "            \"productLineSub\": \"0000\",\n" +
                "            \"productLineSubDesc\": \"全部\",\n" +
                "            \"industryCode\": \"HY001\",\n" +
                "            \"industryName\": \"大健康\",\n" +
                "            \"industryLevel\": \"1\",\n" +
                "            \"xjRateFz\": \"4792059000.0\",\n" +
                "            \"allRateFz\": \"21256693500.0\",\n" +
                "            \"skFenmu\": \"297559053000.0\"\n" +
                "        }],\n" +
                "    \"pageNum\": 1\n" +
                "}";
        DataAliPageDTO<PublicationRateIndustryResponseDTO> responseDTODataAliPageDTO = JSON.parseObject(dataJsonStr, new TypeReference<DataAliPageDTO<PublicationRateIndustryResponseDTO>>(){});
        System.out.println(responseDTODataAliPageDTO);
    }

}
