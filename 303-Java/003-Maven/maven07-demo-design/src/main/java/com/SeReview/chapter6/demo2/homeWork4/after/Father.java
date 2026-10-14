package com.SeReview.chapter6.demo2.homeWork4.after;

/**
 * 练习 4 · 坑 4 —— 在父类构造方法里调用「可被重写的方法」
 *
 * 这是本篇唯一真正值钱的坑，也是《Effective Java》明确点名的反模式。
 * 它和练习 1 其实是同一个形态：练习 1 里「字段初始化器调一个方法」，
 * 这里「构造方法体里调一个方法」—— 只要那个方法可被重写，坑就成立了。
 *
 * 一句话说清它为什么危险：
 *   ★ 动态绑定让方法跑到子类版本，但这时候子类的字段【还没初始化】，
 *     子类于是拿到了一个「半初始化对象」，读到 0 / null / false，而且【完全不报错】。
 */
public class Father {

    public Father() {
        print();          // ❗ 坑就在这一行：构造方法体里调了一个可被重写的方法
    }

    public void print() {                 // public / protected → 子类能重写 → 坑成立
        System.out.println("Father.print");
    }
}
