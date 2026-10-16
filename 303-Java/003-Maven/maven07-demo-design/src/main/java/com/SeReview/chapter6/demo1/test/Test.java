package com.SeReview.chapter6.demo1.test;

/**
 *
 * @author M <musededu25@gmail.com>
 * @since 2026/10/16 22:24
 */


public class Test {

    static void main() {

        Father father = new Father("老王");
        father.show();

        Father son = new Child("小王");
        son.show();
    }
}
