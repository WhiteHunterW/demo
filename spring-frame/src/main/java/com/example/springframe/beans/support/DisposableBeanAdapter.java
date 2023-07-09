package com.example.springframe.beans.support;

import cn.hutool.core.util.StrUtil;
import com.example.springframe.beans.BeanDefinition;
import com.example.springframe.exception.BizException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/7/9
 */
public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;
    private final String beanName;
    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition definition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = definition.getDestroyMethodName();
    }


    @Override
    public void destroy() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).destroy();
        }
        // 2. 配置信息 destroy-method
        if (StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean && "destroy".equals(this.beanName))) {
            Method method = bean.getClass().getMethod(destroyMethodName);
            if (null == method) {
                throw new BizException("could not find a destroy named'" + method + "' method on bean with name '" + beanName + "'");
            }
            method.invoke(bean);
        }
    }
}
