package com.example.biz;

import com.google.common.collect.Lists;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
        final List<String> arrayList = Lists.newArrayList("test1", "test2");
        arrayList.stream().iterator().remove();
        arrayList.stream().filter(Objects::nonNull).map(a -> a + "add").forEach(System.out::println);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        /*FileOutputStream outputStream = null;
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
        }*/

        /*LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(1696780800, 0, ZoneOffset.of("+8"));
        String timeStr = localDateTime.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        System.out.println(timeStr);
*/
        /*
        // Collectors.toMap() key重复的时候错误提示的内容是第一个key对应的value值
        List<User> userList = new ArrayList<>();
        User user = new User();
        user.setName("wenzeng");
        user.setCount(1);
        userList.add(user);
        User user1 = new User();
        user1.setName("wenzeng");
        user1.setCount(2);
        userList.add(user1);
        Map<String, Integer> userMap = userList.stream().collect(Collectors.toMap(User::getName, User::getCount, (a, b) -> {
            throw new BizException("重复value" + a);
        }));
        System.out.println(userMap);*/

        // StringBuilder的append() 拼接字符串时，字符串为空，会拼接null字符串
        String str = null;
        String stringBuilder = "wenzeng" +
                str;
        System.out.printf(stringBuilder);
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
