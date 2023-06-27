package com.example.demo;

import com.example.demo.data.User;
import com.example.demo.data.UserInfoVO;
import com.example.demo.transfer.ConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * @author xingche
 * @date 2022/1/16
 */
@Slf4j
class TestController {

    @Test
    void testConvert(){
        final User user = new User();
        user.setName("testUser");
        user.setGender(1);
        user.setCreated(new Date());
        final UserInfoVO userInfoVO = ConvertUtil.convert(user, UserInfoVO.class, (from, target) -> {
            target.setGender(Objects.equals(from.getGender(), 1) ? "女" : "男");
            target.setCreated(Objects.nonNull(from.getCreated()) ? from.getCreated().getTime() : null);
        });
        log.info("userInfo {}", userInfoVO);
    }

    @Test
    void testAndThen(){
        final Consumer<Integer> consumer = System.out::println;
        final Consumer<Integer> consumer1 = i -> System.out.println(i + 10);
        consumer.andThen(consumer1).accept(3);
    }
}
