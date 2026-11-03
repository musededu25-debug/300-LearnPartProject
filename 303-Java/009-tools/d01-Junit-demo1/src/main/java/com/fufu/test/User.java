 package com.fufu.test;

/**
 *
 * @author M <musededu25@gmail.com>
 * @since 2026/11/03 22:57
 */


public class User {

    public static void getUserName(String name){
        if(name == null){
            System.out.println("名字为空!!!!");
        }
        else {
            System.out.println("用户的名字的长度是" + name.length());
        }
    }

    public static int getMaxIndex(String s){

        if(s == null){
            return -1;
        }
        return s.length()-1;
    }
}
