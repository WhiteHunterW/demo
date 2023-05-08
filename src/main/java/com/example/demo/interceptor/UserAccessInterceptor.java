package com.example.demo.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Function:
 * 就是一个普通的拦截器
 * @author wenzeng
 * @date 2023/4/25
 */
@Slf4j
@Component
public class UserAccessInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("拦截器 -- preHandle {}", System.currentTimeMillis());
        /*
         * 添加用户权限校验逻辑
         */
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("拦截器 -- postHandle {}", System.currentTimeMillis());
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("拦截器 -- afterCompletion {}", System.currentTimeMillis());
        /*
         * 请求结束后，资源清理
         * 比如对threadLocal执行remove方法
         */
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
