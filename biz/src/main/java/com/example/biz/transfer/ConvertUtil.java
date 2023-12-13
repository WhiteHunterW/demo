package com.example.biz.transfer;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

    public static <F, T> T convert(final F from, final Class<T> clazz) {
        return convert(from, clazz, null);
    }

    /**
     * 批量数据转换——附带额外条件
     */
    public static <F, T> List<T> convertList(final List<F> fromList, final Class<T> clazz,
                                             final ManualTransfer<F, T> manualTransfer) {
        if (CollectionUtils.isEmpty(fromList)) {
            return Collections.emptyList();
        }
        final List<T> toList = new ArrayList<>(fromList.size());
        for (final F from : fromList) {
            if (manualTransfer == null) {
                toList.add(convert(from, clazz));
            } else {
                toList.add(convert(from, clazz, manualTransfer));
            }
        }
        return toList;
    }
}
