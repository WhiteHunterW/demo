package com.example.biz.util;

import com.example.biz.data.UserInfoVO;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.validator.HibernateValidator;

import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author wenzeng
 * @date 2024/9/6
 */
public class ValidatorUtil {

    private static final Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }


    /**
     * 校验对象 返回错误信息
     * @param t
     * @param groups
     * @return
     * @param <T>
     */
    public static <T> String validate(T t, Class<?>...groups) {
        Set<ConstraintViolation<T>> msg = validator.validate(t, groups);
        if(CollectionUtils.isNotEmpty(msg)) {
            return msg.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(","));
        }
        return null;
    }

    /**
     * 校验单个字段
     * @param object
     * @param propertyName
     * @param groups
     * @return
     * @param <T>
     */
    public static  <T> String validateProperty(T object, String propertyName, Class<?>... groups) {
        Set<ConstraintViolation<T>> msg = validator.validateProperty(object, propertyName, groups);
        if(CollectionUtils.isNotEmpty(msg)) {
            return msg.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(","));
        }
        return null;
    }

    /**
     * 校验对象指定字段值
     * @param beanType
     * @param propertyName
     * @param value
     * @param groups
     * @return
     * @param <T>
     */
    public static <T> String validateValue(Class<T> beanType, String propertyName, Object value, Class<?>... groups) {
        Set<ConstraintViolation<T>> msg = validator.validateValue(beanType, propertyName, value, groups);
        if(CollectionUtils.isNotEmpty(msg)) {
            return msg.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(","));
        }
        return null;
    }

    public void test() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Configuration<?> configure = Validation.byDefaultProvider().configure();
        Validator validator2 = configure.buildValidatorFactory().getValidator();

        Validator validator1 = Validation.byProvider(HibernateValidator.class).configure().buildValidatorFactory().getValidator();
    }


    public static void main(String[] args) {
        UserInfoVO userInfoVO = new UserInfoVO();
        //System.out.println(ValidatorUtil.validate(userInfoVO));
        userInfoVO.setAddress("北京");
        System.out.println(validateValue(UserInfoVO.class, "address", "南京"));
    }


}
