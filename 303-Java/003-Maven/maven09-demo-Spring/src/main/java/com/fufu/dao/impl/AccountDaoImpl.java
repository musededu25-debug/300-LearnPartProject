package com.fufu.dao.impl;

import com.fufu.dao.AccountDao;
import org.springframework.stereotype.Repository;

/**
 * 【小改】原来写的是 implements com.fufu.dao.AccountDao（全限定名），
 * 功能上没错，但换成 import 更符合阅读习惯。
 */
@Repository(value = "accountDao")
public class AccountDaoImpl implements AccountDao {
    @Override
    public void addAccount() {
        System.out.println("AccountDaoImpl - add success");
    }
}
