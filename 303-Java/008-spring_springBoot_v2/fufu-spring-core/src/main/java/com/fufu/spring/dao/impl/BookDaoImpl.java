package com.fufu.spring.dao.impl;

import com.fufu.spring.dao.BookDao;
import org.springframework.stereotype.Repository;


@Repository
public class BookDaoImpl implements BookDao {

    @Override
    public void save() {
        System.out.println("BookDaoImpl save1");
    }
}
