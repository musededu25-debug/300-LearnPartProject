package com.fufu;
import jakarta.servlet.http.HttpServletRequest;

public class Calc {

    public int add(int a, int b) {
        return a + b;
    }

    public int divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("除数不能为 0");
        }
        return a / b;
    }
}
