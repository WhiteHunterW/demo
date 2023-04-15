package com.example.demo.condition;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Function:
 *
 * @author xingche
 * @date 2023/2/22
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Conditional(CheckCondition.class)
public @interface CheckConditionTest {

    String name();

    String value() default "";
}
