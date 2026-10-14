package com.SeReview.chapter6.demo2.homeWork4.after;

/**
 * 练习 4 · 坑 4（子类）
 *
 * 注意 value 声明在哪【都一样】—— 只要它是子类的【实例字段】，
 * 它的初始化就一定排在「父类构造体」之后，所以永远晚于 Father() 里那次 print() 调用。
 */
public class Child extends Father {

    int value = 100;      // ← 这一句执行时，print() 早就已经跑完了

    @Override
    public void print() {
        System.out.println("Child.print, value=" + value);
    }
}
