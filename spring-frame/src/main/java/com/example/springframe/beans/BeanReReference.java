package com.example.springframe.beans;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/7/1
 */
public class BeanReReference {

    private String beanName;

    public BeanReReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
