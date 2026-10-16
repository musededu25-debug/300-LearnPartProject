package com.fufu.test;

import com.fufu.dao.AccountDao;
import com.fufu.pojo.Account;
import com.fufu.service.AccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AccountTest {


    @Test
    public void test01(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        AccountDao accountDao = (AccountDao) context.getBean("accountDao");

        AccountService accountService = (AccountService) context.getBean("accountService");

        Account account = accountService.findAccountById(1);

        System.out.println(account);
    }

    @Test
    public void test02(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext2.xml");

        AccountDao accountDao = (AccountDao) context.getBean("accountDao");

        AccountService accountService = (AccountService) context.getBean("accountService");

        Account account = accountService.findAccountById(1);

        System.out.println(account);
    }

}
