package com.SeReview.chapter6.demo1.before;

public class Father {

    // ❌【错误 1】你原来写的是  private String name;
    //    private 的含义是「只有 Father 自己能看见」——
    //    所以 Child 里那行 super.name 编译器直接拒绝：
    //        error: name has private access in Father
    //    ✅ 改成 protected：子类可以访问（跨包也能，但只能通过子类实例访问）。
    //
    //    📌 顺带记住一句反直觉的话：private 那份字段【物理上仍然在子类对象的内存里】，
    //       只是子类「写不出它的名字」。所以「不继承」≠「不存在」——
    //       它还躺在对象里，只能靠父类的 getter / setter 间接摸到。
    protected String name;

    public Father() {
    }

    public Father(String name) {
        this.name = name;
    }

    // ❌【错误 2】你原来写的是  public void show(String name) { System.out.println(name); }
    //    参数名 name 和字段名 name 撞了！方法体里裸写的 name 指的是【参数】，不是字段。
    //    结果这个方法跟 name 字段一点关系都没有，既体现不出继承，也体现不出重写。
    // ✅ 去掉参数，让它老老实实打印字段；要字段就必须写 this.name。
    public void show() {
        System.out.println("Father.show() 读到的 name = " + this.name);
    }

    // ✅ 这是父类留下的「正规出口」：子类就算拿不到 private 字段，也能通过它读
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
