package com.example.demo.practice;

/**
 * Function:
 *
 * @author xingche
 * @date 2023/1/26
 */
public class SortAl {

    public static void main(String[] args) {
        int[] array = new int[]{6,4,5,3,1,0,0,7,4};
        //bubbleSortAsc(array);
        //insertSort(array);
        //selectionSort(array);
        //mergeSort(array);
        moveZero(array);
        for (int i : array) {
            System.out.println(i);
        }
    }

    /**
     * 冒泡排序
     * 原地排序，O(n^2),稳定排序
     * @param array
     */
    public static void bubbleSortAsc(int[] array) {
        if(array.length <= 1) {
            return;
        }
        // 每趟两两比较，找到一个最大或最小的
        // 外层只有在全部倒序的时候会遍历到数组末尾，一般情况下在这之前就已经完成排序了
        // 可以加个flag标识一下，如果整趟没有元素移动，就说明已经排好序
        boolean flag;
        for (int i = 0; i < array.length; i++) {
            // 尾部已经排好序的不用再比较了
            flag = false;
            for (int j = 0; j < array.length - i - 1; j++) {
                if(array[j] > array[j+1]) {
                    // 这一行写成大于就是升序，写成小于就是降序
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    flag = true;
                }
            }
            if(!flag) {
                // 没有元素移动提前退出
                break;
            }
        }
    }

    /**
     * 插入排序
     * 原地排序，O(n^2),稳定排序
     * @param array
     */
    public static void insertSort(int[] array) {
        /*
          在一组有序的元素中插入元素，保证插入后的数据也是有序的
          这段有序的元素初始时只有一个元素
         */
        for(int i = 1 ; i < array.length; i++){
            // 这个地方如果不先取出来，后面数据搬移的时候i位置的值直接被覆盖了
            int value = array[i];
            int j = i - 1;
            // 从尾开始往前找插入位置
            for(; j >= 0; j--) {
                if(array[j] > value) {
                    /*
                     条件写成大于就是升序，写成小于就是降序
                     数据搬移的时候，直接覆盖就行了，有序的元素往后挪一个位置
                     */
                    array[j+1] = array[j];
                } else {
                    break;
                }
            }
            array[j+1] = value;
        }
    }

    /**
     * 选择排序
     * 原地排序，O(n^2), 不稳定
     * @param array
     */
    public static void selectionSort(int[] array){
        if(array.length <= 1){
            return;
        }
        // 每次在无序区间选择一个最小或最大的放在有序区间的末尾
        for(int i = 0; i < array.length; i++){
            int temp = Integer.MAX_VALUE;
            int count = Integer.MIN_VALUE;
            for (int j = i; j < array.length; j++){
                if(array[j] < temp){
                    temp = array[j];
                    count = j;
                }
            }
            array[count] = array[i];
            array[i] = temp;
        }
    }

    /**
     * 快速排序
     * 分治，递归
     * @param array
     */
    public static void quickSort(int[] array){
        /*
         任意找一个数，将数组分成两部分，大于这个数的在右边，小于的在左边
         递归分组
         */
        quick_sort(array, 0, array.length - 1);
    }

    public static void quick_sort(int[] array, int p, int r){
        if(p >= r){
            return;
        }
        // 计算分组位置
        int q = partition(array, p, r);
        quick_sort(array, p, q-1);
        quick_sort(array, q+1, r);
    }

    public static int partition(int[] array, int p, int r){
        // 在数组的给定区间内 找到将数组大小分成两部分的位置
        // 以每组的最后一个元素为准
        int temp = array[r];
        int i = p;
        for(int j = p; j <= r-1 ; j++) {
            if(array[j] > temp){
                int val = array[i];
                array[i] = array[j];
                array[j] = val;
                i++;
            }
        }
        int val = array[i];
        array[i] = temp;
        array[r] = val;
        return i;
    }

    /**
     * 分治思想，递归实现
     * 归并排序
     * @param array
     */
    public static void mergeSort(int[] array){
        /*
          将数组分成两部分,从中间开始拆，[p,r]拆成 [p,q]和[q+1,r]
          使每一部分分别有序后，再合并两个有序数组
          直到每部分只有一个元素的时候开始合并
         */
        merge_sort(array, 0, array.length - 1);
    }

    public static void merge_sort(int[] array, int p, int r){
        // 退出递归条件
        if(p >= r) {
            return;
        }
        // 分组
        int q = (p+r) / 2;
        merge_sort(array, p, q);
        merge_sort(array, q+1, r);
        // 合并
        merge(array, p, q, r);
    }

    public static void merge(int[] array, int p, int q, int r){
        // 合并两个有序数组[p,q], [q+1,r]
        int[] temp = new int[array.length];
        int k = 0;
        int i = p;
        int j = q+1;
        while (i <= q && j <= r){
            if(array[i] > array[j]) {
                temp[k++] = array[j++];
            } else {
                temp[k++] = array[i++];
            }
        }
        // 判断哪部分没有拼接完
        while (i <= q){
            temp[k++] = array[i++];
        }
        while (j <= r) {
            temp[k++] = array[j++];
        }
        // 将排好序的两部分数据 放回array数组
        /* 复制的替代方法
        for(int c=0; c <= r-p; c++){
            array[p+c] = temp[c];
        }
        */
        if (r - p + 1 >= 0) {
            System.arraycopy(temp, 0, array, p, r - p + 1);
        }

    }


    public static  void moveZero(int[] array){
        int slow = 0;
        int fast = 0;
        while(fast < array.length){
            if(array[fast] != 0){
                array[slow] = array[fast];
                slow++;
            }
            fast++;
        }
        while(slow < array.length){
            array[slow++] = 0;
        }
    }

}
