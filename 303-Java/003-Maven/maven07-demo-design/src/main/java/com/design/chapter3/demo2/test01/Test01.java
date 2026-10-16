package com.design.chapter3.demo2.test01;

import com.design.chapter3.demo2.test01.impl.Rectangle;
import com.design.chapter3.demo2.test01.impl.Square;

/**
 *
 * @author M <musededu25@gmail.com>
 * @since 2026/10/16 23:15
 */


public class Test01 {



    static void main() {

        Quadrilateral rectangle = new Rectangle(10,5);
        Quadrilateral square = new Square(10);


        RectangleDemo rectangleDemo = new RectangleDemo();

        System.out.println("当前正方形");
        rectangleDemo.printLengthAndWidth(square);

        System.out.println("当前长方形");
        rectangleDemo.printLengthAndWidth(rectangle);
        rectangleDemo.resize((Rectangle) rectangle);
        System.out.println("修改后的长方形");
        rectangleDemo.printLengthAndWidth(rectangle);
    }


}
