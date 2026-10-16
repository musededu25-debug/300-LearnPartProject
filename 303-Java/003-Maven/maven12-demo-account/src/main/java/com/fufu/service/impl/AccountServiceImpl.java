package com.fufu.service.impl;

import com.fufu.dao.AccountDao;
import com.fufu.pojo.Account;
import com.fufu.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author M <musededu25@gmail.com>
 * @since 2026/10/16 21:54
 */

@Service(value = "accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    @Qualifier(value = "accountDao")
    AccountDao accountDao;


    /**
     * 首先判断转出人账户和转入人的账户是否存在，如果存在，继续判断转出人的转账金额是否足够。
     * 如果足够，再执行金额的扣账和入账操作。
     * @param sourceName 转出人的账户姓名
     * @param targetName  转入人的账户姓名
     * @param money  转账金额
     */

    public void transfer(String sourceName, String targetName, Double money) {

        Account sourceAccount = accountDao.findAccountByName(sourceName);
        Account targetAccount = accountDao.findAccountByName(targetName);
        if(sourceAccount != null && targetAccount != null){
            if(sourceAccount.getMoney() >= money){
                //设置转出人的转账金额
                sourceAccount.setMoney(sourceAccount.getMoney() - money);
                //设置转入人的账户金额
                targetAccount.setMoney(targetAccount.getMoney() + money);
                //更新账户
                accountDao.updateAccount(sourceAccount);
                int i = 10 / 0;
                accountDao.updateAccount(targetAccount);
            }
        }


        /*try{
            transactionManager.beginTransaction();
            Account sourceAccount = accountDao.findAccountByName(sourceName);
            Account targetAccount = accountDao.findAccountByName(targetName);
            if(sourceAccount != null && targetAccount != null){
                if(sourceAccount.getMoney() >= money){
                    //设置转出人的转账金额
                    sourceAccount.setMoney(sourceAccount.getMoney() - money);
                    //设置转入人的账户金额
                    targetAccount.setMoney(targetAccount.getMoney() + money);
                    //更新账户
                    accountDao.updateAccount(sourceAccount);
                    //int i = 10 / 0;
                    accountDao.updateAccount(targetAccount);
                }
            }
            transactionManager.commit();
        }catch (Exception e){
            transactionManager.rollBack();
            e.printStackTrace();
        }finally {
            transactionManager.release();
        }*/
    }


}
