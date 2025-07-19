package com.example.biz.design.proxy;

/**
 * @author wenzeng
 * @date 2025/1/15
 */
public class Proxy implements Subject{

    private final RealSubject realSubject;

    public Proxy(RealSubject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public void request() {
        // 1.执行具体的目标对象之前，增加的功能处理

        // 2. 执行具体的目标对象
        realSubject.request();

        // 3.执行具体的目标对象之后，处理逻辑
    }
}
