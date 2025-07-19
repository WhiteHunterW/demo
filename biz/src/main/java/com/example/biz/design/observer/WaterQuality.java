package com.example.biz.design.observer;

/**
 * 水质污染的具体目标对象
 * @author wenzeng
 * @date 2025/1/21
 */
public class WaterQuality extends WaterQualitySubject{


    private int polluteLevel = 0;

    public void setPolluteLevel(int polluteLevel) {
        this.polluteLevel = polluteLevel;
        // 水质改变的时候调用观察者通知
        notifyObservers();
    }

    @Override
    public void notifyObservers() {
        for (WatcherObserver observer : observers) {
            if(polluteLevel >= 0){
                if("监测人员".equals(observer.getJob())) {
                    observer.update(this);
                }
            }
            if(polluteLevel >= 1) {
                if("预警人员".equals(observer.getJob())) {
                    observer.update(this);
                }
            }
            if(polluteLevel >=2){
                if("监测部门领导".equals(observer.getJob())) {
                    observer.update(this);
                }
            }
        }
    }

    @Override
    public int getPolluteLevel() {
        return polluteLevel;
    }

    public static void main(String[] args) {
        WaterQuality subject = new WaterQuality();
        Watcher watcher = new Watcher();
        watcher.setJob("监测人员");
        Watcher watcher1 = new Watcher();
        watcher1.setJob("预警人员");
        Watcher watcher2 = new Watcher();
        watcher2.setJob("监测部门领导");
        subject.attach(watcher);
        subject.attach(watcher1);
        subject.attach(watcher2);
        // 改变水质，引起下游变化
        subject.setPolluteLevel(2);
    }
}
