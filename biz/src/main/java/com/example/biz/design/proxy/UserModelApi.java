package com.example.biz.design.proxy;

/**
 * @author wenzeng
 * @date 2025/1/15
 */
public interface UserModelApi {
    void setName(String name);
    void setUserId(String userId);
    void setDepId(String depId);
    void setSex(String sex);
     String getName();
     String getUserId();
     String getDepId();
     String getSex();
}
