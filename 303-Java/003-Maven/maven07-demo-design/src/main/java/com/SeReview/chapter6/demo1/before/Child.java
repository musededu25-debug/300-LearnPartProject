package com.SeReview.chapter6.demo1.before;

public class Child extends Father {

    // ⚠️【关键点：字段遮蔽（shadow）】父类已经有一个 name，这里又定义了一份。
    //    此刻一个 Child 对象里有【两个互相独立的 name 槽】：
    //        Father.name  ← 父类那一份
    //        Child.name   ← 子类这一份（就是这一行）
    //    它们不会互相覆盖，各自占各自的内存格子。
    //
    //    📌 日常写代码【绝对不要这么干】——正确做法是只留父类那一份、构造里 super(name) 存进去。
    //       这里故意留两份，是为了让你亲眼看到「字段没有多态」。
    protected String name;

    public Child() {
    }

    public Child(String name) {
        // ❌【错误 3】你原来只写了 this.name = name;，没有 super(name)。
        //    后果：编译器自动插入 super()（无参）→ 调到 Father() 空构造
        //          → 父类那一份 name 永远是 null（就算编译过了也拿不到 "lisi"）。
        // ✅ 两份都显式赋值，而且值故意写成不一样，方便你在输出里对比。
        super(name);                        // 父类那一份 = "lisi"
        this.name = name + "（子类那份）";    // 子类那一份 = "lisi（子类那份）"
    }

    // ✅ 这才是真正的重写：方法名、参数列表都和父类一模一样。
    //    @Override 是让编译器帮你校验「这真的是重写吗」——不匹配就直接报错。别删。
    @Override
    public void show() {
        // ❌【错误 4】你原来写的是 super.show(name); —— Father.show 现在不要参数了
        super.show();   // 调父类被重写掉的那个方法 → 它读的是「父类那一份」name

        // 📌 一句话记：this = 「本类这一份」，super = 「父类那一份」。
        System.out.println("Child.show() 里的 this.name  = " + this.name);
        System.out.println("Child.show() 里的 super.name = " + super.name);
    }
}
