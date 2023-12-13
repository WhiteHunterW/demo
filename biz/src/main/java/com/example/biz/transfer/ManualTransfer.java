package com.example.biz.transfer;

/**
 * @author wz
 * @date 2022/1/16
 */
@FunctionalInterface
public interface ManualTransfer<T,F> {

    /**
     * 通用转换方法
     * @param t
     * @param r
     */
    void transform(T t, F r);
}
