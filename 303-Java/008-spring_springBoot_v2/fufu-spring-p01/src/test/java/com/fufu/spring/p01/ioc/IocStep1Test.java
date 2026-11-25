package com.fufu.spring.p01.ioc;

import com.fufu.spring.p01.config.P01Config;
import com.fufu.spring.p01.ioc.dao.BookDao;
import com.fufu.spring.p01.ioc.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(P01Config.class)
public class IocStep1Test {

    @Autowired
    BookService bookService;

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void test(){
        bookService.save("Spring实战");
    }

}
