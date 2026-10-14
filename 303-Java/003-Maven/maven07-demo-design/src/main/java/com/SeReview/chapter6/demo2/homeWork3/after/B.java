package com.SeReview.chapter6.demo2.homeWork3.after;

/** 练习 3 · 三层继承 —— 中间层 B */
public class B extends A {
    static { System.out.println("B-static"); }
    { System.out.println("B-inst"); }
    public B() { System.out.println("B-ctor"); }
}
