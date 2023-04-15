package com.example.demo.transfer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

/**
 * @author wz
 * @date 2022/1/16
 */
@Slf4j
public class ConvertUtil {

    public static <F, T> T convert(F f, Class<T> t, ManualTransfer<F, T> manualTransfer){
        if(Objects.isNull(f)){
            return null;
        }
        // 获取类型实例
        T target = null;
        try {
             target = t.newInstance();
        } catch (Exception e){
            log.error("获取实例对象失败");
        }
        convert(f, target);
        if(Objects.nonNull(manualTransfer)){
            manualTransfer.transform(f, target);
        }
        return target;
    }

    public static <F, D> void convert(F from, D data){
        if(Objects.isNull(from) || Objects.isNull(data)){
            return;
        }
        BeanUtils.copyProperties(from, data);
    }
}
