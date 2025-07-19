package com.example.biz.design.iterator;

/**
 * 另一个工资管理类
 * @author wenzeng
 * @date 2025/1/22
 */
public class SalaryManager extends AbstractAggregate{

    /**
     * 数组管理工资
     */
    private PayModel[] pms = null;

    public PayModel[] getPms() {
        return pms;
    }


    public int size() {
        return pms.length;
    }

    public PayModel get(int index) {
        if(index > -1 && index < size()) {
            return pms[index];
        }
        return null;
    }




    /**
     * 计算工资
     */
    public void calcSalary() {
        pms = new PayModel[2];
        PayModel pay1 = new PayModel();
        pay1.setUserName("王五");
        pay1.setPay(3000);
        pms[0] = pay1;
        PayModel pay2 = new PayModel();
        pay2.setUserName("赵六");
        pay2.setPay(20000);
        pms[1] = pay2;
    }

    @Override
    public Iterator createIterator() {
        return new ArrayIteratorImpl(this);
    }
}
