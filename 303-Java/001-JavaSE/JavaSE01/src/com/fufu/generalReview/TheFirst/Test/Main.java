package com.fufu.generalReview.TheFirst.Test;

import java.util.Arrays;

public class Main {

    static void main() {

        Waiter Raj = new Waiter();
        Waiter Simran = new Waiter();

        Raj.tables = new int[]{4, 5, 6};
        Simran.tables = new int[]{2, 3};

        System.out.println(Arrays.toString(Raj.tables));
        System.out.println(Arrays.toString(Simran.tables));
        System.out.println("=================");

        Chef amit =new Chef();

//        Waiter raj = new Waiter(amit)

    }
}
