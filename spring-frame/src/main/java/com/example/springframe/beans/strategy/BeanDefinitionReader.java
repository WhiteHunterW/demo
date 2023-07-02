package com.example.springframe.beans.strategy;

import com.example.springframe.core.io.Resource;
import com.example.springframe.core.io.ResourceLoader;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/7/2
 */
public interface BeanDefinitionReader {

    /**
     * 获取bean元数据注册对象
     * @return
     */
    BeanDefinitionRegistry getRegistry();

    /**
     * 获取资源加载对象
     * @return
     */
    ResourceLoader getResourceLoader();

    /**
     * 加载资源里的bean
     * @param resource
     */
    void loadBeanDefinitions(Resource resource);

    /**
     * 多资源解析
     * @param resources
     */
    void loadBeanDefinitions(Resource...resources);

    /**
     * 根据资源路径解析
     * @param location
     */
    void loadBeanDefinitions(String location);

}
