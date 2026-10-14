package com.SeReview.chapter6.demo2.homeWork3.after;

/** 练习 3 · 三层继承 —— 最底层 C */
public class C extends B {
    static { System.out.println("C-static"); }
    { System.out.println("C-inst"); }
    public C() { System.out.println("C-ctor"); }
}
