package com.SeReview.chapter6.demo1.test;

/**
 *
 * @author M <musededu25@gmail.com>
 * @since 2026/10/16 22:22
 */


public class Child extends Father{

    public Child(String name) {
        super(name);
    }

    @Override
    public void show() {
        System.out.println("Child-show");
    }

}
