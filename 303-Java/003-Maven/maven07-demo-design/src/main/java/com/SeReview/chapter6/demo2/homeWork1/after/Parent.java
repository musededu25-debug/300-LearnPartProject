package com.SeReview.chapter6.demo2.homeWork1.after;

/**
 * 练习 1 · 修正版（父类）
 *
 * 你原来那版【顺序完全正确】，只有两处没按目标输出：
 *   ① 一个静态字段都没写        → 目标的前两行压根没出现
 *   ② 实例块写在了实例字段前面  → 先后正好和目标相反
 *
 * 修正只有一句话：
 *   ★ 同类内谁先谁后，唯一由「它写在第几行」决定 ——
 *     不存在「块一定在字段前」这种固定规则。想让字段排在块前面，把它挪上去就行。
 *
 * 所以本类里静态字段、实例字段各放一个，专门让你看清这条规则。
 */
public class Parent {

    // ↓ 写在静态块【前面】→ 输出里它就排在静态块前面
    static int a = initStatic("Parent 静态字段");

    static {
        System.out.println("Parent 静态块");
    }

    // ↓ 写在实例块【前面】→ 输出里它就排在实例块前面
    int b = initInstance("Parent 实例字段");

    {
        System.out.println("Parent 实例块");
    }

    public Parent() {
        System.out.println("Parent 构造体");
    }

    // ★ 这两个小工具方法【故意写成 private】。
    //   因为「字段初始化器里调用一个方法」这个形态，只要方法可被重写，
    //   立刻就会变成练习 4 的坑 4（父类初始化阶段调到子类版本，读到默认值）。
    //   这里先用 private 躲开它，练习 4 再把这个坑专门演一遍。
    private static int initStatic(String s) {
        System.out.println(s);
        return 1;
    }

    private int initInstance(String s) {
        System.out.println(s);
        return 1;
    }
}
