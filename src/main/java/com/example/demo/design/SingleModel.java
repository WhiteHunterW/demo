package com.example.demo.design;


/**
 * Function:
 *
 * @author xingche
 * @date 2023/2/7
 */
public class SingleModel {

    /**
     * 饿汉模式
     */
    private static SingleModel instance = new SingleModel();

    public static SingleModel getInstance(){
        return instance;
    }

    /**
     * 懒汉模式的双检锁保证并发安全
     */
    private volatile static SingleModel singleModel;

    public static SingleModel getSingleModel(){
        if(singleModel == null) {
            synchronized (SingleModel.class){
                if(singleModel == null){
                    singleModel = new SingleModel();
                }
            }
        }
        return singleModel;
    }

}
