package com.SeReview.chapter6.demo1.test;

/**
 *
 * @author M <musededu25@gmail.com>
 * @since 2026/10/16 22:21
 */


public class Father {

    protected String name;

    public Father() {
    }

    public Father(String name) {
        this.name = name;
    }

    public void show()
    {
        System.out.println("Father-Show");
    }
}
