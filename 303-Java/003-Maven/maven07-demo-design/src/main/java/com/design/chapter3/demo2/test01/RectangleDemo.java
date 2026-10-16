package com.design.chapter3.demo2.test01;

import com.design.chapter3.demo2.test01.impl.Rectangle;

/**
 *
 * @author M <musededu25@gmail.com>
 * @since 2026/10/16 23:09
 */


public class RectangleDemo {

    public void resize(Rectangle rectangle){
        while(rectangle.getWidth()<=rectangle.getLength()){
            rectangle.setWidth(rectangle.getWidth() + 1.0);
        }
    }

    public void printLengthAndWidth(Quadrilateral quadrilateral){
        System.out.println(quadrilateral.toString());
    }
}
