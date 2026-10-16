package com.design.chapter3.demo2.test01.impl;

import com.design.chapter3.demo2.test01.Quadrilateral;

import java.util.Queue;

/**
 *
 * @author M <musededu25@gmail.com>
 * @since 2026/10/16 23:03
 */


public class Square implements Quadrilateral {

    private double side;

    public Square(double side) {
        this.side = side;
    }

    public Square() {
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }


    @Override
    public double getLength() {
        return side;
    }

    @Override
    public double getWidth() {
        return side;
    }

    @Override
    public String toString() {
        return "Square{" +
                "side=" + side +
                '}';
    }
}
