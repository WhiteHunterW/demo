package com.example.biz.design.proxy;

import com.example.biz.practice.FileUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author wenzeng
 * @date 2025/1/17
 */
@Slf4j
public class UerProxy implements UserModelApi{

    private UserModel userModel;

    /**
     * 是否加载过数据
     */
    private boolean loaded = false;

    public UerProxy(UserModel userModel) {
        this.userModel = userModel;
    }

    @Override
    public void setName(String name) {
        userModel.setName(name);
    }

    @Override
    public void setUserId(String userId) {
        userModel.setUserId(userId);
    }

    @Override
    public void setDepId(String depId) {
        userModel.setDepId(depId);
    }

    @Override
    public void setSex(String sex) {
        userModel.setSex(sex);
    }

    @Override
    public String getName() {
        return userModel.getName();
    }

    @Override
    public String getUserId() {
        return userModel.getUserId();
    }

    @Override
    public String getDepId() {
        if(!this.loaded) {
            // 没有加载过，从数据库取 这里用文件加载的问题是，每次查看详情都会加载全量，然后筛选出当前对象对应的某一条数据；
            // 如果使用数据库可以根据人员ID具体的查询某一条对应的数据
            log.info("查询详情");
            List<UserModel> userModels = FileUtils.readerBuffer("/Users/wenzeng/Desktop/wz/模版/人员详情.txt", UserModel.class);
            userModels.forEach(user -> {
                if(this.getUserId().equals(user.getUserId())) {
                    userModel = user;
                }
            });
            loaded = true;
        }
        return userModel.getDepId();
    }

    @Override
    public String getSex() {
        if(!this.loaded) {
            // 没有加载过，从数据库获取
            loaded = true;
        }
        return userModel.getSex();
    }
}
