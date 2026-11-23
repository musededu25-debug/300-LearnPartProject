package com.fufu.spring.test;

import com.fufu.spring.config.SpringConfig;
import com.fufu.spring.dao.BookDao;
import com.fufu.spring.domain.Brand;
import com.fufu.spring.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.sql.DataSource;
@SpringJUnitConfig(SpringConfig.class)
public class Test01 {

    @Autowired
    @Qualifier("bookDao2")
    private BookDao bookDao;

    @Test
    public void test01() {
        //初始化和销毁
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        BookService bookService = context.getBean(BookService.class);
        bookService.save();

        DataSource dataSource = context.getBean(DataSource.class);
        System.out.println(dataSource);
    }


    @Test
    public void test02() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        bookDao.save();
    }
}
