package com.example.springframe.support;

import com.example.springframe.bean.BeanDefinition;
import com.example.springframe.config.BeanFactory;

import java.util.Objects;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/6/8
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {


    @Override
    public Object getBean(String beanName) {
        Object obj = getSingletonBean(beanName);
        if (Objects.nonNull(obj)) {
            return obj;
        }
        // 为空怎么办？ 创建一个新的？
        BeanDefinition definition = getBeanDefinition(beanName);
        return createBean(beanName, definition);
    }


    public Object getBean(String beanName, BeanDefinition beanDefinition, Object[] args) {
        return null;
    }

    /**
     * 获取bean元数据
     *
     * @param beanName
     * @return
     * @throws Exception
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName);


    /**
     * 创建bean实例
     *
     * @param beanName
     * @param beanDefinition
     * @return
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition);


}
