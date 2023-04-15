package com.example.demo.practice;


import com.google.inject.internal.util.Maps;


import java.lang.reflect.InvocationTargetException;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import java.util.Stack;

/**
 * @author xingche
 * @date 2022/2/26
 */
public class Test {

    public void test(){
        Map<String, List<String>> map = Maps.newHashMap();
        List<List<String>> result = new ArrayList<>(map.values());
        String[] strings = "dfdf".split(",");
        for (String str : strings){
            StringBuilder builder = new StringBuilder();
            builder.delete(0, builder.length());
            int num = Integer.parseInt(str);
            if(num > 255 || num < 0 || str.startsWith("0")){

            }
        }
        System.out.println(strings.length);
        map.get("11").add("11");
    }



    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        /*Class<?> cl = User.class;
        User user = (User) cl.newInstance();
        user.setName("xingche");
        System.out.println(user.getName());
        Constructor<?> constructor = cl.getConstructor();
        User user1 = (User) constructor.newInstance();
        user1.setName("xingche2");
        System.out.println(user1.getName());
        Object obj = new Object();*/

        String String = "String";
        System.out.println(String);
    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= Integer.MAX_VALUE) ? Integer.MAX_VALUE : n + 1;
    }


    public static String validIPAddress(String queryIP) {
        if(queryIP.contains(".")){
            String[] strGroup = queryIP.split("\\.");
            if(strGroup.length != 4){
                return "Neither";
            }
            for(String str : strGroup){
                int num = Integer.parseInt(str);
                if(num > 255 || num < 0 || str.startsWith("0")){
                    return "Neither";
                }
            }
            return "IPv4";
        } else if(queryIP.contains(":")){
            String[] strGroup = queryIP.split(":");
            if(strGroup.length != 8){
                return "Neither";
            }

        }
        return "Neither";
    }

    public static String removeOuterParentheses(String s) {
        Stack<Character> originLanguage = new Stack();
        StringBuilder builder = new StringBuilder();
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                builder.append("(");
                originLanguage.push(s.charAt(i));
            } else if(s.charAt(i) == ')'){
                if(!originLanguage.empty()){
                    builder.append(")");
                    originLanguage.pop();
                }
                if(originLanguage.empty()){
                    String groupStr = builder.toString();
                    if(groupStr.length() > 2){
                        result.append(groupStr, 1, groupStr.length() -1);
                    }
                    builder = builder.delete(0, builder.length());
                }
            }
        }
        return result.toString();
    }
}
