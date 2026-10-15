package com.fufu.Service.impl;

import com.fufu.Service.AccountService;
import com.fufu.dao.AccountDao;

public class AccountServiceImpl implements AccountService {

    AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public void add() {
        accountDao.addAccount();
    }
}
