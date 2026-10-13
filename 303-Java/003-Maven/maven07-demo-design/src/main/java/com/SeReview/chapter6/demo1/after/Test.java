package com.SeReview.chapter6.demo1.after;

public class Test {

    static void main() {

        Father father = new Father("zhangsan");

        Father child = new Child("lisi");

        System.out.println("father.show()=========");
        father.show();


        System.out.println("child.show()=======");
        child.show();
    }
}
