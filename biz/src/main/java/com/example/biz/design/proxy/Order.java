package com.example.biz.design.proxy;

/**
 * @author wenzeng
 * @date 2025/1/20
 */
public class Order implements OrderApi {

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 订单编号
     */
    private int orderNum;

    /**
     * 下单人
     */
    private String orderUser;

    public Order(String productName, int orderNum, String orderUser) {
        this.productName = productName;
        this.orderNum = orderNum;
        this.orderUser = orderUser;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName, String orderUser) {
        this.productName = productName;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum, String orderUser) {
        this.orderNum = orderNum;
    }

    public String getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(String orderUser, String user) {
        this.orderUser = orderUser;
    }
}
