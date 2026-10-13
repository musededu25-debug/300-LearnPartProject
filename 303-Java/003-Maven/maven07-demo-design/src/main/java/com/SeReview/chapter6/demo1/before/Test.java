package com.SeReview.chapter6.demo1.before;

public class Test {

    // ❌【错误 5】你原来写的是  static void main()
    //    这在你的 JDK 27 上确实能跑（JEP 512 新特性），但笔记的基线是 JDK 21，
    //    那边必须写成下面这样，否则 java Test 会报「找不到 main 方法」。
    public static void main(String[] args) {

        Father father = new Father("zhangsan");

        // ✅ 向上转型：父类引用 → 子类对象。自动完成，不用强转，也永远不会失败。
        //    📌 记住这个变量有两副面孔：
        //       编译期类型（左边） = Father   ← 决定「你能调用哪些方法/字段」
        //       运行时类型（右边） = Child    ← 决定「方法真正跑哪一份实现」
        Father child = new Child("lisi");

        System.out.println("===== 1. father.show() =====");
        father.show();

        System.out.println("===== 2. child.show() =====");
        // ❌【错误 6】你原来写的是 child.show(); 但那时 show 还需要一个 String 参数，编译报错：
        //        method show in class Father cannot be applied to given types
        // ✅ show() 现在不要参数了。这行调的是 Child 重写后的版本 —— 这就是「方法看右边」。
        child.show();

        System.out.println("===== 3. 字段看左边：同一个对象里真的有两份 name =====");
        // child 的编译期类型是 Father → 拿到的是【父类那一份】
        System.out.println("child.name            = " + child.name);
        // 强转成 Child 之后，才拿得到【子类那一份】。两份值不一样，证明它们互不相干。
        System.out.println("((Child) child).name  = " + ((Child) child).name);
        // 方法看右边，但 Child 没有重写 getName()，所以跑的还是父类版本 → 读父类那一份
        System.out.println("child.getName()       = " + child.getName());
    }
}
