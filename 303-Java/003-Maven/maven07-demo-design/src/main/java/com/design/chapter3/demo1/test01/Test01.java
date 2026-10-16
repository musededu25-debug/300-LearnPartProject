package com.design.chapter3.demo1.test01;

/**
 *
 * @author M <musededu25@gmail.com>
 * @since 2026/10/16 22:52
 */


public class Test01 {

    static void main() {

        TermShow termShow = new TermShow();

        AbstractSkin skin1 = new DefaultSpecificSkin();
        AbstractSkin skin2 = new FuFuSkin();

        termShow.show(skin1);
        termShow.show(skin2);

    }
}
