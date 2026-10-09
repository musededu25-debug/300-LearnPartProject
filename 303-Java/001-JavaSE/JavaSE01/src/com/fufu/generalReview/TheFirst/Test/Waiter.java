package com.fufu.generalReview.TheFirst.Test;

public class Waiter {

    //tables加static才属于类本身不加不能Waiter.tables，而在python里面可以
    public int[] tables;

    private String name;

    private Chef  chef;

    public Waiter() {
    }

//    public Waiter(Chef chef) {
//        this( chef,null);
//    }

    public Waiter(int[] tables) {
        this.tables = tables;
    }

    public void takeOrder(String dish){
        System.out.println(this.name +"takes order"+ dish);
        this.chef.cookFood();
    }
}
