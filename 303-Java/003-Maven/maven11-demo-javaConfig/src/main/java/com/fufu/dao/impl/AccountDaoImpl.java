package com.fufu.dao.impl;

import com.fufu.dao.AccountDao;
import com.fufu.pojo.Account;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository(value = "accountDao")
public class AccountDaoImpl implements AccountDao {

    // 操作数据库的核心类
    @Autowired
    private QueryRunner runner;

//    // 提供 setter 方法供 Spring 依赖注入
//    public void setRunner(QueryRunner runner) {
//        this.runner = runner;
//    }

    @Override
    public List<Account> findAllAccount() {
        try {
            String sql = "select * from account";
            List<Account> accountList = runner.query(sql, new BeanListHandler<Account>(Account.class));
            return accountList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account findAccountById(Integer accountId) {
        try {
            String sql = "select * from account where id = ?";
            Account account = runner.query(sql, new BeanHandler<Account>(Account.class), accountId);
            return account;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveAccount(Account account) {
        try {
            String sql = "insert into account(name, money) values(?, ?)";
            runner.update(sql, account.getName(), account.getMoney());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateAccount(Account account) {
        try {
            String sql = "update account set name = ?, money = ? where id = ?";
            runner.update(sql, account.getName(), account.getMoney(), account.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAccount(Integer accountId) {
        try {
            String sql = "delete from account where id = ?";
            runner.update(sql, accountId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}