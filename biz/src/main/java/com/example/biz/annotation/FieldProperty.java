package com.example.biz.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/11/7
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldProperty {

    /**
     * 字段名
     * @return
     */
    String name() default "";

    /**
     * 指标名 映射到目标对象的字段名
     * @return
     */
    String[] indexName() default "";

    /**
     * 排序
     * @return
     */
    int sort() default 0;
}
