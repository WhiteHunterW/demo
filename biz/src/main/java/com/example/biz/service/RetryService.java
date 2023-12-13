package com.example.biz.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeoutException;

/**
 * Function:
 *
 * @author wz
 * @date 2022/7/31
 */
@Slf4j
@Service
public class RetryService {

    @Resource(name = "retryTemplate")
    private RetryTemplate retryTemplate;

    @Retryable(recover = "helloRecovery",include = {NumberFormatException.class, TimeoutException.class},
            maxAttempts = 5,backoff = @Backoff(delay = 1000)
    )
    public void testException(String number) throws TimeoutException {
        /*
          @Retryable(recover = "helloRecovery",
            include = {NumberFormatException.class, TimeoutException.class},
            maxAttemptsExpression = "${retry.maxAttempts}",
            exceptionExpression = "${number.format}",
            backoff = @Backoff(delayExpression = "${delay.time}",value = 10))
         */
        log.info("enter testException");
        throw new TimeoutException();
        //System.out.println("success");
    }

    @Recover
    public void helloRecovery(TimeoutException e, String number){
        // notice developer
        System.out.println("retry" + number);
    }


    public void turnNumber(final String number) throws TimeoutException {
        log.info("enter turnNumber");
        retryTemplate.execute(context -> {
            /*
             *  execute的RetryCallback参数就是需要执行重试的操作 businessService
             */
            testException(number);
            return context.getLastThrowable();
        }, callback -> {
            /*
             * RecoveryCallback:重试失败后的兜底操作
             */
            log.error("retry3");
            return callback.getLastThrowable();
        });
    }
}
