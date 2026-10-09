package com.fufu;

import org.apache.commons.lang3.StringUtils;

public class Demo {

    public static void main(String[] args) {
        String raw = "hello maven";
        System.out.println(StringUtils.capitalize(raw));
        System.out.println(StringUtils.reverse(raw));
    }
}
