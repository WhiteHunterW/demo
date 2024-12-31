package com.example.biz.handler;

import com.example.biz.data.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author wenzeng
 * 自定义全局异常处理器
 * @date 2024/12/19
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NullPointerException.class)
    //@ControllerAdvice + @ResponseBody 相当于@RestControllerAdvice
    public Response<Void> nullException(Exception e) {
        log.info("空指针异常", e);
        return Response.error("系统异常，请联系管理员");
    }
}
