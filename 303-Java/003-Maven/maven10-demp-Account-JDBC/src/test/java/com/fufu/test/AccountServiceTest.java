package com.fufu.test;

import com.fufu.pojo.Account;
import com.fufu.service.AccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class AccountServiceTest {

    @Test
    public void testFindAll() {
        // 1. 获取 Spring 核心容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        // 2. 获取业务层对象
        AccountService as = ac.getBean("accountService", AccountService.class);
        // 3. 执行方法
        List<Account> accounts = as.findAllAccount();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }
}