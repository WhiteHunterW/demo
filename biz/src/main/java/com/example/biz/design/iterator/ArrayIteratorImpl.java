package com.example.biz.design.iterator;

/**
 * @author wenzeng
 * @date 2025/1/22
 */
public class ArrayIteratorImpl implements Iterator{

    private final SalaryManager salaryManager;

    private int index = -1;

    public ArrayIteratorImpl(SalaryManager salaryManager) {
        this.salaryManager = salaryManager;
    }

    @Override
    public void first() {
        index = 0;
    }

    @Override
    public void next() {
        if(index < salaryManager.size()) {
            index += 1;
        }
    }

    @Override
    public boolean isDone() {
        return index == salaryManager.size();
    }

    @Override
    public Object currentItem() {
        return salaryManager.get(index);
    }
}
