package com.example.biz.pool;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author wenzeng
 * @date 2023/12/5
 */
@Slf4j
public class SumTask extends RecursiveTask<Long> {

    /**
     * 元数据
     */
    private Integer[] elementData;

    /**
     * 数组起始位置
     */
    private int start;

    /**
     * 数组结束位置
     */
    private int end;

    /**
     * 单个任务能处理的长度阈值
     */
    private int threshold = 50;


    public SumTask(Integer[] elementData, int start, int end) {
        this.elementData = elementData;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        // 数组长度
        if(end - start <= threshold) {
            Long result = 0L;
            for (int i = start; i < end; i++) {
                result += elementData[i];
            }
            return result;
        }
        int middle = (start + end) / 2;
        SumTask task = new SumTask(elementData, start, middle);
        SumTask task1 = new SumTask(elementData, middle, end);
        invokeAll(task, task1);
        /*
        Long result3 = task1.compute();
        Long result4 = task.join();
        return result3 + result4;*/
        Long result1 = task.join();
        Long result2 = task1.join();
        return result1 + result2;
    }


    public static void main(String[] args) {
        Integer[] array = new Integer[200];
        for (int i = 0; i < 200; i++) {
            array[i] = i;
        }
        List<Integer> list = new ArrayList<>(Arrays.asList(array));
        list.parallelStream().forEach(System.out::println);
        SumTask sumTask = new SumTask(array, 0, array.length);
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        System.out.print(forkJoinPool.invoke(sumTask));
    }
}
