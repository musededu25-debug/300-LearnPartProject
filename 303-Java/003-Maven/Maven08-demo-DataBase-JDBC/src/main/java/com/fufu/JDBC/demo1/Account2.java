package com.fufu.JDBC.demo1;

public class Account2 {

    private String account;

    private double balance;

    private int state;

    public Account2(String account, double balance, int state) {
        this.account = account;
        this.balance = balance;
        this.state = state;
    }

    public Account2() {
    }

    @Override
    public String toString() {
        return "Account2{" +
                "account='" + account + '\'' +
                ", balance=" + balance +
                ", state=" + state +
                '}';
    }
}
