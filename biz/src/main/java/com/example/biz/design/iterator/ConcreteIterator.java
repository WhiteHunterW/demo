package com.example.biz.design.iterator;

/**
 * @author wenzeng
 * @date 2025/1/21
 */
public class ConcreteIterator implements Iterator{


    /**
     * 被迭代的聚合对象
     */
    private final ConcreteAggregate aggregate;

    /**
     * 内部索引 记录当前迭代的位置
     */
    private int index = -1;

    public ConcreteIterator(ConcreteAggregate aggregate) {
        this.aggregate = aggregate;
    }

    @Override
    public void first() {
        index = 0;
    }

    @Override
    public void next() {
        if(index < this.aggregate.size()) {
            index += 1;
        }
    }

    @Override
    public boolean isDone() {
        return index == aggregate.size();
    }

    @Override
    public Object currentItem() {
        return aggregate.get(index);
    }
}
