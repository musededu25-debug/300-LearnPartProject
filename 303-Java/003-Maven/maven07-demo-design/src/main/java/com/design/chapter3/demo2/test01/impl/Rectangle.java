package com.design.chapter3.demo2.test01.impl;

import com.design.chapter3.demo2.test01.Quadrilateral;

/**
 *
 * @author M <musededu25@gmail.com>
 * @since 2026/10/16 23:01
 */


public class Rectangle implements Quadrilateral {

    private double length;
    private double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public Rectangle() {
    }

    @Override
    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    @Override
    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "length=" + length +
                ", width=" + width +
                '}';
    }
}
