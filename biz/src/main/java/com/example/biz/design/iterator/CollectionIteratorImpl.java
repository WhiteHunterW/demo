package com.example.biz.design.iterator;

/**
 * @author wenzeng
 * @date 2025/1/22
 */
public class CollectionIteratorImpl implements Iterator{

    private final PayManager payManager;

    private int index = -1;


    public CollectionIteratorImpl(PayManager payManager) {
        this.payManager = payManager;
    }

    @Override
    public void first() {
        index = 0;
    }

    @Override
    public void next() {
        if(index < payManager.size()) {
            index += 1;
        }
    }

    @Override
    public boolean isDone() {
        return index == payManager.size();
    }

    @Override
    public Object currentItem() {
        return payManager.get(index);
    }
}
