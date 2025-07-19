package com.example.biz.util;

import com.example.biz.data.User;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/7/26
 */
public class SortUtil {

    private static final List<String> SORT = Arrays.asList("xingche","wenzeng", "xiaowen");

    public static void main(String[] args) {
        /*List<User> list = new ArrayList<>();
        User user = new User();
        user.setName("wenzeng");
        list.add(user);
        User user1 = new User();
        user1.setName("xingche");
        list.add(user1);
        User user2 = new User();
        user2.setName("xiaowen");
        list.add(user2);
        User user3 = new User();
        user3.setName("dawen");
        System.out.println("排序前");
        list.forEach(System.out::println);
        list.sort((o1, o2) -> {
            int sort1 = SORT.indexOf(o1.getName());
            int sort2 = SORT.indexOf(o2.getName());
            return Integer.compare(sort1, sort2);
        });
        *//*System.out.println("排序后");
        list.forEach(System.out::println);*//*
        int res = list.stream().filter(Objects::nonNull)
                .findFirst()
                .map(User::getName)
                .filter(StringUtils::isNotEmpty)
                .map(t -> t.indexOf(2)).orElseGet(null);
        System.out.println(res);*/


        /*Pattern p = Pattern.compile("cat");
        Matcher m = p.matcher("one cat two cats in the yard");
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "dog");
        }
        m.appendTail(sb);
        System.out.println(sb);*/
        String b = "";
        String c = "";
        String a = StringUtils.isNotEmpty(b) ? b : StringUtils.isNotEmpty(c) ? c : "adfff" + ",abcd";
        System.out.println(a);
    }

}
