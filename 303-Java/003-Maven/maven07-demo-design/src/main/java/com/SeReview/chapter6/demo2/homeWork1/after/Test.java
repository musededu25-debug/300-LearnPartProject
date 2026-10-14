package com.SeReview.chapter6.demo2.homeWork1.after;

/**
 * 练习 1 · 修正版 —— 目标输出与真实输出（已跑通，一字不差）
 *
 * First==========
 * Parent 静态字段      ┐ 静态：父类先、子类后；同类内按书写顺序（字段在块前）
 * Parent 静态块        │
 * Son 静态字段         │
 * Son 静态块           ┘
 * Parent 实例字段      ┐
 * Parent 实例块        │ 实例：每个对象都要把「父成员→父构造→子成员→子构造」走一遍
 * Parent 构造体        │
 * Son 实例字段         │
 * Son 实例块           │
 * Son 构造体           ┘
 * Second==========
 * Parent 实例字段      ┐
 * Parent 实例块        │ ★ 静态那四行没有再次出现 —— 类初始化只发生一次
 * Parent 构造体        │
 * Son 实例字段         │
 * Son 实例块           │
 * Son 构造体           ┘
 *
 * 📌 对比你原来那版：你少了两个「静态字段」行，而且「实例块」跑到了「实例字段」前面。
 *    原因就是书写顺序 —— 你把 { } 写在了 int a = ... 上面。
 */
public class Test {

    public static void main(String[] args) {

        System.out.println("First==========");
        new Son();                      // 第一次 new：静态 + 实例全走

        System.out.println("Second==========");
        new Son();                      // 第二次 new：只有实例部分，静态不再执行
    }
}
