package com.example.biz.design.iterator;

/**
 * 聚合对象的抽象接口
 * @author wenzeng
 * @date 2025/1/21
 */
public abstract class AbstractAggregate {

    /**
     * 创建迭代器的工厂方法
     * @return
     */
    public abstract Iterator createIterator();

}
