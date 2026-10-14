package com.SeReview.chapter6.demo2.homeWork1.before;

public class Son extends Parent{
   static {
       System.out.println("2-Son-static{}");
   }

    {
        System.out.println("6-Son-{}");
    }


    int b = initIntB();
    public int initIntB() {
        System.out.println("7-Son-initInt()");
        return 1;
    }

    public Son() {
        System.out.println("8-Son-gouzao");
    }
}
