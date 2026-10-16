package com.fufu.test;

import com.fufu.dao.AccountDao;
import com.fufu.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAccount {

    public static void main(String[] args) {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        AccountDao accountDao = (AccountDao) context.getBean("accountDao");

        accountDao.addAccount();

        AccountService accountService = (AccountService) context.getBean("accountService");
        accountService.addAccountService();

    }


}
