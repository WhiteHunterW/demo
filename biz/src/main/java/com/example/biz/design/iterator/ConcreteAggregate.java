package com.example.biz.design.iterator;

/**
 * @author wenzeng
 * @date 2025/1/21
 */
public class ConcreteAggregate extends AbstractAggregate{

    /**
     * 聚合对象具体的内容
     */
    private final String[] ss;


    public ConcreteAggregate(String[] ss) {
        this.ss = ss;
    }

    @Override
    public Iterator createIterator() {
        return new ConcreteIterator(this);
    }

    /**
     * 获取索引下的对象
     * @param index
     * @return
     */
    public Object get(int index) {
        Object object = null;
        if(index < ss.length) {
            object = ss[index];
        }
        return object;
    }

    /**
     * 获取聚合对象的大小
     * @return
     */
    public int size() {
        return ss.length;
    }
}
