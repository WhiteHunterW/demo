package com.example.demo.practice;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * Function:
 *
 * @author xingche
 * @date 2023/3/13
 */
public class ScannerTest {
    static int[] arr = new int[8];
    public static void main(String[] args) {
        int[] e = new int[2];
        System.out.println(arr[0]);
        System.out.println(e[0]);
        /*arrayInsert();
        strInsert();

        varStrInsert();*/


        //hexToTen();

        //deleteStr();
        int m = 0;
        for (int i = 1; i <= 15; i+=4){
            for (int j = 3; j <= 19; j+=4){
                m++;
            }
        }
        System.out.println(m);
    }

    /**
     * 删除出现次数最少的字符
     */
    private static void deleteStr() {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        String str = in.nextLine();
        Map<Character, Integer> map = new HashMap<>(str.length());
        for (int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            Integer re = map.get(c);
            if(re == null){
                re = 0;
            }
            re += 1;
            map.put(c, re);
        }
        Collection<Integer> values = map.values();
        int min = values.stream().min(Integer::compareTo).get();
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < str.length(); i++){
            Integer c = map.get(str.charAt(i));
            if(min != c){
                s.append(str.charAt(i));
            }
        }
        System.out.println(s);
    }

    /**
     * 16进制转10 输入了0x
     */
    private static void hexToTen() {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        String str = in.nextLine();
        String s = str.substring(2);
        System.out.println(Integer.parseInt(s, 16));
    }

    /**
     * 多个测试用例，每个测试用例一行。
     *
     * 每行通过空格隔开，有n个字符，n＜100
     * 对于每组测试用例，输出一行排序过的字符串，每个字符串通过空格隔开
     */
    private static void varStrInsert() {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String str = in.nextLine();
            String[] arra = str.split("\\s");
            List<String> col = Arrays.asList(arra).stream().sorted().collect(Collectors.toList());
            col.forEach(s -> System.out.printf(s + " "));
            System.out.println();


            /*
             * 逗号分隔
             */
            StringJoiner joiner = new StringJoiner(",");
            col.forEach(s -> joiner.add(s));
            System.out.println(joiner);
        }
    }

    /**
     * 输入数据有多组, 每行表示一组输入数据。
     * 每行不定有n个整数，空格隔开。(1 <= n <= 100)。
     */
    private static void arrayInsert() {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            // 注意 while 处理多个 case
            int sum = 0;
            String str = in.nextLine();
            String[] arr = str.split("\\s");
            for(String s : arr){
                sum += Integer.parseInt(s);
            }
            System.out.println(sum);

        }
    }

    /**
     * 输入有两行，第一行n
     *
     * 第二行是n个字符串，字符串之间用空格隔开
     */
    private static void strInsert() {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int n = in.nextInt();
        String[] arra = new String[n];
        for(int i = 0; i < n; i++){
            String s = in.next();
            arra[i] = s;
        }
        List<String> col = Arrays.stream(arra).sorted().collect(Collectors.toList());
        col.forEach(s -> System.out.printf(s + " "));
    }


}
