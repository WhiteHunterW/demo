package com.example.springframe.core.io;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/7/2
 */
public interface ResourceLoader {

    String CLASS_PATH_URL_PREFIX = "classpath:";

    /**
     * 获取资源
     * @param location
     * @return
     */
    Resource getResource(String location);


}
