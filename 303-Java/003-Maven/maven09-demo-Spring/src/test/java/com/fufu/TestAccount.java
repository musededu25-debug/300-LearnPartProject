package com.fufu;

import com.fufu.dao.impl.AccountDaoImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAccount {

    @Test
    public void test01(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");


        AccountDaoImpl accountService = (AccountDaoImpl) context.getBean("accountService");

    }

}
