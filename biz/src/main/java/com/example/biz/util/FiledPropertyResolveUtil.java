package com.example.biz.util;

import com.alibaba.fastjson.JSON;
import com.example.biz.annotation.FieldProperty;
import com.example.biz.data.StatisticsModel;
import com.example.common.BizException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;
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

    /**
     * 解析注解 获取字段排序map
     * 传入indexName为空时 对整个类中加了注解的字段排序
     * 传入indexName不为空时，对类中加了注解且注解indexName不为空且 indexName = 'All' 或 等于传入值的字段排序
     *
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> Map<Integer, Field> resolvedFiledProperty(Class<T> tClass, String indexName) {
        Field[] fields = tClass.getDeclaredFields();
        Map<Integer, Field> sortFieldMap = new HashMap<>(16);
        for (Field field : fields) {
            if (field.isAnnotationPresent(FieldProperty.class)) {
                FieldProperty property = field.getAnnotation(FieldProperty.class);
                List<String> indexNameList = Arrays.asList(property.indexName());
                if (StringUtils.isEmpty(indexName) && CollectionUtils.isNotEmpty(indexNameList)) {
                    if (!indexNameList.contains(indexName) && !indexNameList.contains("All")) {
                        continue;
                    }
                }
                int sort = property.sort();
                if (sort > 0) {
                    if (sortFieldMap.containsKey(sort)) {
                        log.error("字段 {} sort 值 {} 重复", field.getName(), sort);
                        throw new BizException(field.getName() + "的顺序" + "【" + property.sort() + "】" + " 重复");
                    }
                    sortFieldMap.put(sort, field);
                }
            }
        }
        return sortFieldMap;
    }


    /**
     * 返回分组排序的字段map
     *
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> Map<String, Map<Integer, Field>> resolvedFieldByGroup(Class<T> tClass) {
        Map<String, Map<Integer, Field>> resultMap = new HashMap<>(16);
        Field[] fields = tClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(FieldProperty.class)) {
                FieldProperty property = field.getAnnotation(FieldProperty.class);
                String[] indexNameList = property.indexName();
                if (indexNameList == null || indexNameList.length == 0) {
                    continue;
                }
                String indexName = indexNameList[0];
                Map<Integer, Field> sortFieldMap = resultMap.get(indexName);
                if (MapUtils.isEmpty(sortFieldMap)) {
                    sortFieldMap = new HashMap<>(16);
                }
                int sort = property.sort();
                if (sort > 0) {
                    if (sortFieldMap.containsKey(sort)) {
                        log.error("字段 {} sort 值 {} 重复", field.getName(), sort);
                        throw new BizException(field.getName() + "的顺序" + "【" + property.sort() + "】" + " 重复");
                    }
                    sortFieldMap.put(sort, field);
                }
                resultMap.put(indexName, sortFieldMap);
            }
        }
        return resultMap;
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
