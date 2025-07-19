package com.example.biz.design.proxy;

/**
 * @author wenzeng
 * @date 2025/1/15
 */
public class UserModel {

    private String name;

    private String userId;

    private String depId;

    private String sex;

    public UserModel() {
        // 空构造
    }

    public UserModel(String name, String userId) {
        this.name = name;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
