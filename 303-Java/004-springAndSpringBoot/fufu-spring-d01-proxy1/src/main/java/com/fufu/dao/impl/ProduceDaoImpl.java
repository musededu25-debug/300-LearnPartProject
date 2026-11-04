package com.fufu.dao.impl;

import com.fufu.dao.ProduceDao;

import java.lang.reflect.Proxy;

public class ProduceDaoImpl implements ProduceDao {
    @Override
    public void buyProduce(String name) {
        System.out.println(name + "购买了电脑");
    }
}
