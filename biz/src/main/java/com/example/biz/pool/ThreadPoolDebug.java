package com.example.biz.pool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author wenzeng
 * @date 2024/6/28
 */
public class ThreadPoolDebug {

    private static final ThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();
    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();


    public static int calculate(int num) {
        int result = 0;
        for (int i = 1; i < num; i++) {
            result += i;
        }
        return result;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //testCompleteFuture();
        testInheritableThreadLocal();
    }

    public static void testInheritableThreadLocal() {
        inheritableThreadLocal.set("set-inheritableThreadLocal");
        threadLocal.set("set-threadLocal");
        new Thread(() -> {
            String threadLocalCtx = threadLocal.get();
            String inheritableThreadLocalCtx = inheritableThreadLocal.get();
            System.out.println("currentThread:" + Thread.currentThread() + ", get threadLocalCtx:" + threadLocalCtx);
            System.out.println("currentThread:" + Thread.currentThread() + ", get inheritableThreadLocalCtx:" + inheritableThreadLocalCtx);
        }).start();
    }




    /**
     * CompletableFuture
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void testCompleteFuture() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> calculate(100));
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> calculate(200));
        // 主线程等待任务完成
        CompletableFuture.allOf(future, future1).join();
        // 调用join或get获取单个任务的返回值,会立即返回,因为上面已经等待过了
        System.out.println(future.get() + future1.get());
    }

}
