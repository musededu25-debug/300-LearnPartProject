package com.fufu.spring.p01.ioc.dao.impl;

import com.fufu.spring.p01.ioc.dao.BookDao;
import org.springframework.stereotype.Repository;

@Repository
public class BookDaoImpl implements BookDao {

    @Override
    public void save(String bookName) {
        System.out.println("BookDaoImpl save" + bookName);
    }
}
