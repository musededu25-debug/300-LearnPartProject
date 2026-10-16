//package com.fufu.dao.impl;
//
//import com.fufu.dao.AccountDao;
//import com.fufu.pojo.Account;
//import com.fufu.utils.ConnectionUtils;
//import org.apache.commons.dbutils.QueryRunner;
//import org.apache.commons.dbutils.handlers.BeanHandler;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import java.sql.SQLException;
//
///**
// *
// * @author M <musededu25@gmail.com>
// * @since 2026/10/16 21:53
// */
//
//@Repository(value = "accountDao")
//public class AccountImpl implements AccountDao {
//
//    @Autowired
//    QueryRunner queryRunner;
//
////    @Autowired
////    ConnectionUtils connectionUtils;
//
//    //根据用户名查询账户信息是否存在
//    public Account findAccountByName(String name){
//        try {
//            String sql = "select * from account where name = ?";
//            Account account = queryRunner.query(connectionUtils.getConnection(),sql, new BeanHandler<Account>(Account.class),name);
//            return account;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }
//
////    //更新账户信息
////    public void updateAccount(Account account) {
////        try {
////            String sql = "update account set money = ? where name = ?";
////            queryRunner.update(connectionUtils.getConnection(),sql,account.getMoney(),account.getName());
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////    }
//
//}
