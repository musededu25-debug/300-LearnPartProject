package com.SeReview.chapter6.demo2.homeWork2.after;

/**
 * 练习 2 · this(...) 链（父类）
 *
 * 本题的全部机关都在子类 Sub() 里的那一句 this("默认")。
 * 只要记住一句话，三个问题全有答案：
 *
 *   ★ this(...) 不是「创建一个新对象」，而是「把执行权交给本类的另一个构造方法」。
 *
 * 所以：
 *   · Sub() 自己的构造体一行都不会执行（它只是转交）
 *   · 真正干活的是 Sub(String)
 *   · 因此父类构造方法【总共只被调用一次】，而且调的是带参那个
 *   · 那个无参的 Base() 从头到尾没人碰 → 输出里永远看不到 "Base()"
 */
public class Base {

    { System.out.println("Base 实例块"); }

    public Base()         { System.out.println("Base()"); }

    public Base(String s) { System.out.println("Base(String)"); }
}
