package com.example.biz.practice;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Scanner;


/**
 * Function:
 *
 * @author xingche
 * @date 2023/3/17
 */
public class AlTest {
    public static void main(String[] args) {
        //ramdon();
        //ipToInt();
        //strFind();
        //preAdd();

    }

    private static void preAdd() {
        Scanner scanner = new Scanner(System.in);
        Scanner in = new Scanner(System.in);
        int n = scanner.nextInt();
        String s = in.nextLine();
        String[] strArray = s.split("\\s");
        int[] array = new int[500];
        for(int i = 0; i < strArray.length; i++){
            array[i] = Integer.parseInt(strArray[i]);
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++){
            int j = i;
            int sum = 0;
            while (j < array.length){
                sum += array[j];
                if (sum <= n) {
                    max = Math.max(max, sum);
                    j++;
                } else {
                    break;
                }
            }
        }
        System.out.println(max);
    }

    private static void strFind() {
        String temp = null;
        int count = 0;
        Scanner scanner = new Scanner(System.in);
        String A = scanner.nextLine();
        String B = scanner.nextLine();
        int fast = 0,slow = 0;
        while (fast < A.length() && slow < B.length()){
            if(A.charAt(fast) == B.charAt(slow)){
                temp = A.substring(0, fast) + A.substring(fast+1);
                if(slow == B.length() - 1){
                    slow = 0;
                    fast = 0;
                    count++;
                    A = temp;
                } else {
                    slow++;
                }
            }
            fast++;
        }
        System.out.println(count);
    }

    /**
     * 整数和ip地址转换
     */
    private static void ipToInt() {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        int a = in.nextInt();
        String[] sarray = s.split("\\.");
        StringBuilder builder = new StringBuilder();
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setGroupingUsed(false);
        numberFormat.setMaximumIntegerDigits(8);
        numberFormat.setMinimumIntegerDigits(8);
        for (String str : sarray) {
            Integer num = Integer.parseInt(str);
            builder.append(numberFormat.format(Integer.parseInt(Integer.toBinaryString(num))));
        }

        System.out.println(Integer.parseInt(builder.toString(), 2));
    }

    /**
     * 明明的随机数
     * 数组排序后去重
     */
    private static void ramdon() {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int n = in.nextInt();
        int[] array = new int[n];
        for(int i = 0;i < n; i++){
            array[i] = in.nextInt();
        }
        array = Arrays.stream(array).sorted().toArray();
        int slow = 0;
        int fast = 0;
        while (fast < n){
            if(array[fast] != array[slow]) {
                slow++;
                array[slow] = array[fast];
            }
            fast++;
        }
        for (int i = 0; i <= slow; i++) {
            System.out.println(array[i]);
        }
    }
}
