package com.fufu.service.impl;

import com.fufu.dao.AccountDao;
import com.fufu.pojo.Account;
import com.fufu.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service( value = "accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    @Qualifier(value = "accountDao")
    private AccountDao accountDao;

//    // 提供 setter 方法供 Spring 依赖注入
//    public void setAccountDao(AccountDao accountDao) {
//        this.accountDao = accountDao;
//    }

    @Override
    public List<Account> findAllAccount() {
        return accountDao.findAllAccount();
    }

    @Override
    public Account findAccountById(Integer accountId) {
        return accountDao.findAccountById(accountId);
    }

    @Override
    public void saveAccount(Account account) {
        accountDao.saveAccount(account);
    }

    @Override
    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    @Override
    public void deleteAccount(Integer accountId) {
        accountDao.deleteAccount(accountId);
    }
}