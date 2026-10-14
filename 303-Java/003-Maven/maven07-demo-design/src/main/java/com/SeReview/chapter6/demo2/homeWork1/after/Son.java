package com.SeReview.chapter6.demo2.homeWork1.after;

/**
 * 练习 1 · 修正版（子类）
 *
 * 规则和父类一模一样：静态字段写静态块前，实例字段写实例块前。
 *
 * 📌 两个工具方法为什么换了名字（initStaticSon / initInstanceSon）？
 *    因为父类那两个是 private —— **private 成员不继承**（笔记 1.2），
 *    子类里再写同名同参的方法**不算重写、也不算隐藏**，只是碰巧同名的两个新方法。
 *    法律上完全合法，但读代码的人容易误以为在重写，所以这里改成不同名字。
 */
public class Son extends Parent {

    static int c = initStaticSon("Son 静态字段");

    static {
        System.out.println("Son 静态块");
    }

    int d = initInstanceSon("Son 实例字段");

    {
        System.out.println("Son 实例块");
    }

    public Son() {
        System.out.println("Son 构造体");
    }

    private static int initStaticSon(String s) {
        System.out.println(s);
        return 1;
    }

    private int initInstanceSon(String s) {
        System.out.println(s);
        return 1;
    }
}
