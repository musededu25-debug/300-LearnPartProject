package com.SeReview.chapter6.demo1.after;

public class Child extends Father{

    public Child(String name) {
        super(name);
    }

    @Override
    public void show() {
        System.out.println("super.show()=========");
        super.show();

        System.out.println("this.show()========");
        System.out.println(this.name);
    }
}
