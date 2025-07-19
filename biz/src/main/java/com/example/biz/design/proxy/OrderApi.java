package com.example.biz.design.proxy;

/**
 * @author wenzeng
 * @date 2025/1/20
 */
public interface OrderApi {

    String getProductName();
    String getOrderUser();
    void setProductName(String productName, String user);
    void setOrderNum(int orderNum, String user);
    void setOrderUser(String orderUser, String user);
    int getOrderNum();
}
