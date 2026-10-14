package com.SeReview.chapter6.demo2.homeWork3.after;

/** 练习 3 · 三层继承 —— 最顶层 A */
public class A {
    static { System.out.println("A-static"); }
    { System.out.println("A-inst"); }
    public A() { System.out.println("A-ctor"); }
}
