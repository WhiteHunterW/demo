package com.example.biz.design.iterator;

/**
 * @author wenzeng
 * @date 2025/1/21
 */
public interface Iterator {

    void first();

    void next();

    /**
     * 是否到聚合对象的最后一个位置
     * @return
     */
    boolean isDone();

    Object currentItem();

}
