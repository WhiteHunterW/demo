package com.example.springframe.service;

import java.util.HashMap;
import java.util.Map;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/7/5
 */
public class UserManager {

    private static Map<String, String> STRING_MAP = new HashMap<>();

    static {
        STRING_MAP.put("1", "test1");
        STRING_MAP.put("2", "test2");
        STRING_MAP.put("3", "test3");
    }


    public String getUserName(String userId) {
        return STRING_MAP.get(userId);
    }

}
