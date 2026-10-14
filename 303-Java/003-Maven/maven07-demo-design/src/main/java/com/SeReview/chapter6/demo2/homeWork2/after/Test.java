package com.SeReview.chapter6.demo2.homeWork2.after;

/**
 * 练习 2 · 目标输出与真实输出（已跑通，一字不差）
 *
 * Base 实例块
 * Base(String)
 * Sub 实例块
 * Sub(String)
 *
 * ─────────────────────────────────────────────────────────
 * 三个问题的答案：
 *
 * ① 为什么输出里【没有 "Base()"】？父类无参构造到底被调了没有？
 *    → 没有被调。因为唯一调用父类构造的地方是 Sub(String) 里的 super(s)，
 *      而它调的是 Base(String)。Base() 从头到尾没被任何人碰到。
 *
 * ② 输出里【也没有 "Sub()"】—— Sub() 的方法体执行了吗？
 *    → 没有执行。this("默认") 是「转交」，不是「调用后返回」。
 *      Sub() 的方法体里除了这一句什么都没有，而且这一句本身就意味着
 *      「我不干活了，交给 Sub(String)」。所以 Sub() 的构造体一行都没跑。
 *
 * ③ 为什么「Sub 实例块」被夹在 Base(String) 之后、Sub(String) 之前？
 *    → 因为实例初始化块/字段赋值，属于「最终那个调了 super() 的构造方法」，
 *      位置就在它的 super() 返回之后、自己的构造体之前。
 *      注意：Sub 的实例块【只执行了一次】，并没有因为 Sub() 转交过一次就跑两遍 ——
 *      转交的那个构造方法根本不参与实例初始化。
 *      顺序拼起来就是：先走完父类那一整轮（Base 实例块 → Base(String)），
 *      再走子类这一轮（Sub 实例块 → Sub(String)）。
 */
public class Test {

    public static void main(String[] args) {
        new Sub();
    }
}
