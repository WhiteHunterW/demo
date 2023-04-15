package com.example.demo;


import com.google.common.collect.Lists;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
/**
 * @author w.z
 * @date 2022/1/15
 */
public class  FunctionInterfaceTest<T> implements Predicate<T> {

    public static void iteratorList(){
        final List<String> arrayList = Arrays.asList("test1","test2");
        arrayList.stream().filter(Objects::nonNull).map(a -> a + "add").forEach(System.out::println);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FileOutputStream outputStream = null;
        List<String> arr = Lists.newArrayList("xingche", "dingtee");
        try {
            outputStream = new FileOutputStream("/Users/wenzeng/Desktop/wz/workspace/xhiteam/demo/src/main/resources/user.json", true);
            StringJoiner joiner = new StringJoiner(",");
            arr.forEach(joiner::add);
            outputStream.write(joiner.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if(outputStream != null) {
                outputStream.close();
            }
        }
        
        
        
    }

    public static void predicate(){
        System.out.println(Predicate.isEqual("str").test("str"));
    }

    @Override
    public boolean test(T t) {
        return false;
    }

    public static void andThen(){
        final Consumer<String> consumer = String::toLowerCase;
        final Consumer<String> stringConsumer = System.out::println;
        stringConsumer.andThen(consumer);
        consumer.andThen(stringConsumer).accept("sfrewrFFF");
    }

    public static void test(){
        final List<Integer> array = new ArrayList<>();
        array.add(1);
        array.add(2);
        List<Integer> numList = getNumList(1, () -> 2 * 100);

        for (Integer num : numList) {
            System.out.println(num);
        }
    }



    //需求：产生指定个数的整数，并放入集合中
    public static List<Integer> getNumList(int num, Supplier<Integer> sup){
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            Integer n = sup.get();
            list.add(n);
        }

        return list;
    }


    public static void apply() {
        // apply()
        final Function<Integer, Integer> function = i -> i * i;
        System.out.println(function.apply(2));
        // compose()
        final Function<Integer, Integer> stringFunction = i -> i * 2;
        System.out.println(function.compose(stringFunction).apply(2));
        // andThen()
        System.out.println(function.andThen(stringFunction).apply(2));
    }


}
