package com.SeReview.chapter6.demo2.homeWork1.before;

public class Parent {

    static {
        System.out.println("1-Parent-static{}");
    }

    {
        System.out.println("3-Parent-{}");
    }

    int a = initIntA();
    public int initIntA(){
        System.out.println("4-Parent-initInt()");
        return 1;
    }

    public Parent() {
        System.out.println("5-Parent-gou_zao");
    }
}
