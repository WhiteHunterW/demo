package com.example.biz.listener;

import com.alibaba.fastjson.JSON;
import com.example.biz.event.UserChangeEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * Function:
 * SpringEvent
 * 用户变更事件监听器
 * @author wenzeng
 * @date 2023/10/28
 */
@Slf4j
@Service
public class UserChangeEventListener {


    @EventListener(classes = {UserChangeEvent.class})
    public void userChangeListener(UserChangeEvent changeEvent) {
        log.info("userChangeListener -- change param {}", JSON.toJSONString(changeEvent));
    }

}
