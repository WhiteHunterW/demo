package com.example.springframe.beans.factory;

import java.util.List;
import java.util.Map;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/7/2
 */
public interface ListableBeanFactory extends BeanFactory {

    /**
     * 根据类型获取beans
     *
     * @param type
     * @param <T>
     * @return
     */
    <T> Map<String, T> getBeansOfType(Class<T> type);

    /**
     * 获取注册的所有bean名称
     *
     * @return
     */
    List<String> getBeanDefinitionNames();
}
