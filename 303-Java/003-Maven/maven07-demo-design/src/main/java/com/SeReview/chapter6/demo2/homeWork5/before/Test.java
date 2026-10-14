package com.SeReview.chapter6.demo2.homeWork5.before;

public class Test {

    static void main() {
        System.out.println("1.no T");

        T t;
        System.out.println("2.no new T but T t;");

        System.out.println("3.read_T.count " + T.COUNT);

        System.out.println("4.read-again_T.count " + T.COUNT);

        t = new T();
        System.out.println("new T()");

        System.out.println("==============");
        System.out.println("得出结论static里面的代码" +
                "只有在用里面的static成员变量或是new的时候init");
    }
}
