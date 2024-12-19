package com.example.biz.design.adapter;

import java.util.List;

/**
 * 文件操作适配成DB操作
 * 使用场景：接口不兼容，且基本功能已有实现
 * 按照适配器模式的实现方式，可以定义一个类来实现第二版的接口，然后在内部实现的时候，转调第一版已经实现了的功能
 * 这样就可以通过对象组合的方式，既复用了第一版已有的功能，同时又在接口上满足了第二版调用的要求。
 *
 * @author wenzeng
 * @date 2024/12/11
 */
public class Adapter implements LogDBOperatorApi {

    /**
     * 需要适配的对象
     */
    private final LogFileOperateApi adaptee;

    public Adapter(LogFileOperateApi adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void create(List<LogModel> logModels) {
        adaptee.writeFile(logModels);
    }

    @Override
    public void remove(String logId) {
        // 全部读出来，删了重新写
        List<LogModel> logModels = adaptee.readFile();
        logModels.removeIf(logModel -> logModel.getLogId().equals(logId));
        adaptee.writeFile(logModels);
    }

    @Override
    public List<LogModel> listAllLog() {
        return adaptee.readFile();
    }
}
