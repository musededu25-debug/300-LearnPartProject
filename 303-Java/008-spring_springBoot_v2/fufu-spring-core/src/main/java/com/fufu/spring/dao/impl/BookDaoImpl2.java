package com.fufu.spring.dao.impl;

import com.fufu.spring.dao.BookDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;


@Repository("bookDao2")
public class BookDaoImpl2 implements BookDao {

    @Value("${name}")
    String name ;

    @Override
    public void save() {
        System.out.println(System.currentTimeMillis());
        System.out.println("BookDaoImpl save2" + "name:" + name);
    }
}
