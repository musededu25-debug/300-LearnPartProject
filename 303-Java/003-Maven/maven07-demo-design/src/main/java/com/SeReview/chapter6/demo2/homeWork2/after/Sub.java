package com.SeReview.chapter6.demo2.homeWork2.after;

/**
 * 练习 2 · this(...) 链（子类）
 */
public class Sub extends Base {

    { System.out.println("Sub 实例块"); }

    public Sub() {
        // ★ 关键的一行。它必须是构造方法的第一条语句（和 super(...) 同理，
        //   因为「第一条只能有一条」，所以 this(...) 和 super(...) 不能共存 —— 笔记 1.3 硬规则 2）。
        this("默认");          // 转交给本类的 Sub(String)，自己下面的构造体【永远不会执行】
    }

    public Sub(String s) {
        super(s);             // ← 全流程里唯一一次调用父类构造
        System.out.println("Sub(String)");
    }
}
