package com.example.demo.listener;


import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

/**
 * @author xingche
 * @description
 * @date 2022/7/17
 */
public class StringTest extends StringUtils {

    public static boolean containsWhitespace(@Nullable CharSequence str, String er, Double tt) {
        if (!hasLength(str)) {
            return false;
        }

        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}
