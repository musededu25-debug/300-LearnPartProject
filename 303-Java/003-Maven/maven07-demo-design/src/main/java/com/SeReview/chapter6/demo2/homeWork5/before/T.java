package com.SeReview.chapter6.demo2.homeWork5.before;

public class T {

    static {
        System.out.println("init T");
    }

    //从上往下执行，若是上面静态代码块里面要用到COUNT的话会为0
    static int COUNT = 1;
}
