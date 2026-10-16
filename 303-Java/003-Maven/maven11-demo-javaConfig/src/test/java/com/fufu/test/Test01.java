package com.fufu.test;

import com.fufu.config.SpringConfiguration;
import com.fufu.dao.AccountDao;
import com.fufu.pojo.Account;
import com.fufu.service.AccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test01 {

    @Test
    public void test01(){
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);

        AccountService accountService = (AccountService) context.getBean("accountService");

        AccountDao accountDao = (AccountDao) context.getBean("accountDao");

        Account account = accountService.findAccountById(1);

        System.out.println(account);
    }
}
