package com.jasper.demo.utils;

/**
 * @author Jasper.Zhong
 */
public class StringUtils {
    public static boolean isBlank(String str) {
        return str == null || str.length() == 0;
    }
}
