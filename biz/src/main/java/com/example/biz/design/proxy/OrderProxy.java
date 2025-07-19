package com.example.biz.design.proxy;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @author wenzeng
 * @date 2025/1/20
 */
@Slf4j
public class OrderProxy implements OrderApi{

    private Order order = null;

    public OrderProxy(Order order) {
        this.order = order;
    }

    @Override
    public String getProductName() {
        return order.getProductName();
    }

    @Override
    public String getOrderUser() {
        return order.getOrderUser();
    }

    public void setProductName(String productName, String user) {
        // 设置修改权限， 只有订单创建人才能修改
        if(StringUtils.isNotEmpty(user) && Objects.equals(user, getOrderUser())) {
            order.setProductName(productName, user);
        } else {
            log.error("当前用户:{} 无权修改订单产品名称", user);
        }
    }

    @Override
    public void setOrderNum(int orderNum, String user) {
        if(StringUtils.isNotEmpty(user) && Objects.equals(user, getOrderUser())) {
            order.setOrderNum(orderNum, user);
        } else {
            log.error("当前用户:{} 无权修改订单数量", user);
        }
    }

    @Override
    public void setOrderUser(String orderUser, String user) {
        if(StringUtils.isNotEmpty(user) && Objects.equals(user, getOrderUser())) {
            order.setOrderUser(orderUser, user);
        } else {
            log.error("当前用户:{}无权修改订单的订购人", user);
        }
    }

    @Override
    public int getOrderNum() {
        return order.getOrderNum();
    }
}
