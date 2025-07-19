package com.example.biz.design.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * 已有的工资管理对象
 * @author wenzeng
 * @date 2025/1/22
 */
public class PayManager extends AbstractAggregate{

    /**
     * 聚合对象 工资列表
     */
    private final List<PayModel> list = new ArrayList<>();

    public List<PayModel> getList() {
        return list;
    }

    public int size() {
        return list.size();
    }

    public PayModel get(int index) {
        if(index < size()) {
            return list.get(index);
        }
        return null;
    }


    /**
     * 计算工资
     */
    public void calcPay() {
        PayModel pay1 = new PayModel();
        pay1.setPay(1000);
        pay1.setUserName("张三");
        list.add(pay1);
        PayModel pay2 = new PayModel();
        pay2.setUserName("李四");
        pay2.setPay(2000);
        list.add(pay2);
    }

    @Override
    public Iterator createIterator() {
        return new CollectionIteratorImpl(this);
    }
}
