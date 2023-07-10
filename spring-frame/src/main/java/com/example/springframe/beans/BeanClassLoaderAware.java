package com.example.springframe.beans;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/7/10
 */
public interface BeanClassLoaderAware extends Aware{

    /**
     * 设置类加载器
     * @param classLoader
     */
    void setBeanClassLoader(ClassLoader classLoader);
}
