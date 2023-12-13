package com.example.biz.practice;

/**
 * Function:
 *
 * @author xingche
 * @date 2023/3/24
 */
public class PracticeTest {

    public static void main(String[] args) {
        yuesefu(new int[]{5,2,3,4,8,6}, 4);

    }

    /**
     * 约瑟夫问题简单版
     * n个人围成圆圈，每次数到M个杀掉，下一个从头开始数；直到只剩一个人
     */
    private static void yuesefu(int[] array, int m) {
        boolean[] flagArray = new boolean[array.length];
        int countFalse = array.length;
        int num = 0;
        int i = 0;
        while (countFalse > 1) {
            if(!flagArray[i]) {
                num++;
            }
            if(num == m) {
                countFalse--;
                System.out.println(array[i]);
                flagArray[i] = true;
                num = 0;
            }
            i = (i+1) % array.length;
        }
    }
}
