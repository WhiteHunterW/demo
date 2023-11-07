package com.example.demo.util;

import com.alibaba.fastjson.JSON;
import com.example.common.BizException;
import com.example.demo.annotation.FieldProperty;
import com.example.demo.data.StatisticsModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/11/7
 */
@Slf4j
public class FiledPropertyResolveUtil {


    /**
     * 解析 FieldProperty
     * @param t 目标对象
     * @param <T>
     * @return
     */
    public static <T> List<String> parsObjectFiled(T t) {
        Class<?> cl = t.getClass();
        Field[] fields = cl.getDeclaredFields();
        List<String> result = new ArrayList<>();
        Map<Integer, Field> sortFieldMap = new HashMap<>(16);
        for (Field field : fields) {
            if(field.isAnnotationPresent(FieldProperty.class)) {
                FieldProperty property = field.getAnnotation(FieldProperty.class);
                int sort = property.sort();
                if(sort > 0) {
                    if(sortFieldMap.containsKey(sort)) {
                        log.error("字段 {} sort 值 {} 重复", field.getName(), sort);
                        throw new BizException(field.getName() + "的顺序" + "【"+  property.sort()+ "】"+ " 重复");
                    }
                    sortFieldMap.put(sort, field);
                }
            }
        }
        List<Integer> sortValueList = sortFieldMap.keySet().stream().sorted().collect(Collectors.toList());
        sortValueList.forEach(k -> {
            Field field = sortFieldMap.get(k);
            ReflectionUtils.makeAccessible(field);
            Object obj = ReflectionUtils.getField(field, t);
            String value = null;
            if(obj instanceof String) {
                value = (String) obj;
            } else if(obj instanceof Integer) {
                value = String.valueOf(obj);
            } else if(obj instanceof BigDecimal) {
                value = ((BigDecimal) obj).toPlainString();
            }
            if(Objects.isNull(value)) {
                log.error("字段 {} 获取值失败 filed info {}", field.getName(), JSON.toJSONString(field));
                throw new BizException("解析字段值失败");
            }
            result.add(value);
        });
        return result;
    }

    public static void main(String[] args) {
        StatisticsModel model = new StatisticsModel();
        model.setWorkOrderCount(20);
        model.setPointPositionCount(10);
        model.setDecimal(new BigDecimal("0.78"));
        model.setStr("测试");
        List<String> result = parsObjectFiled(model);
        result.forEach(System.out::println);
    }

}
