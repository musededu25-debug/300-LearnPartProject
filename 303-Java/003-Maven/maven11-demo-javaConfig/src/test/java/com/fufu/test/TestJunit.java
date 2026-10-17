package com.fufu.test;

import com.fufu.config.SpringConfiguration;
import com.fufu.pojo.Account;
import com.fufu.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfiguration.class})
public class TestJunit {

    @Autowired
    private AccountService accountService;

    @Test
    public void test01(){
        Account account = accountService.findAccountById(1);
        System.out.println(account);
    }
}
