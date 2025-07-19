package com.example.biz.design.iterator;

import com.alibaba.fastjson.JSON;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author wenzeng
 * @date 2025/1/21
 */
public class IteratorTest {



    public static void main(String[] args) {
        //testAggregateV2();
        testAggregateV3();
    }

    /**
     * 迭代器方法测试
     */
    public static void testAggregate() {
        ConcreteAggregate concreteAggregate = new ConcreteAggregate(new String[]{"111","22323","3243", "54646"});
        ConcreteIterator iterator = new ConcreteIterator(concreteAggregate);
        // 将游标设置到第一个元素
        iterator.first();
        // 开始迭代
        while (!iterator.isDone()) {
            Object obj = iterator.currentItem();
            System.out.println(JSON.toJSONString(obj));
            iterator.next();
        }
    }

    public static void testAggregateV2() {
        // 两套管理代码
        PayManager manager = new PayManager();
        manager.calcPay();
        Collection<PayModel> payModels = manager.getList();
        Iterator iterator = payModels.iterator();
        while (iterator.hasNext()) {
            PayModel pm = (PayModel) iterator.next();
            System.out.println(JSON.toJSONString(pm));
        }

        SalaryManager salaryManager = new SalaryManager();
        salaryManager.calcSalary();
        PayModel[] pays = salaryManager.getPms();
        System.out.println("新收购的公司工资列表：");
        for (PayModel payModel : pays) {
            System.out.println(JSON.toJSONString(payModel));
        }
    }

    public static void testAggregateV3() {
        // 两种不同的数据调用相同的迭代器遍历
        PayManager payManager = new PayManager();
        payManager.calcPay();
        test(payManager.createIterator());
        SalaryManager salaryManager = new SalaryManager();
        salaryManager.calcSalary();
        test(salaryManager.createIterator());
    }


    private static void test(com.example.biz.design.iterator.Iterator iterator) {
        iterator.first();
        while (!iterator.isDone()) {
            System.out.println(JSON.toJSONString(iterator.currentItem()));
            iterator.next();
        }
    }
}
