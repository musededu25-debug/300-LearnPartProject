package com.fufu;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;
import jakarta.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalcTest {

    @Test
    void add_should_return_sum() {
        Calc calc = new Calc();
        assertEquals(5, calc.add(2, 3));
    }

    @Test
    void divide_should_return_quotient() {
        Calc calc = new Calc();
        assertEquals(2, calc.divide(10, 5));
    }

    @Test
    void divide_should_return_quotient2() {
        Calc calc2 = new Calc();
        assertEquals(3, calc2.divide(15, 5));
    }
}
