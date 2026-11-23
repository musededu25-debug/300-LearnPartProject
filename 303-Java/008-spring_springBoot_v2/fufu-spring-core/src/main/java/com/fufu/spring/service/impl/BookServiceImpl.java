package com.fufu.spring.service.impl;

import com.fufu.spring.dao.BookDao;
import com.fufu.spring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class BookServiceImpl implements BookService {

    @Autowired
    @Qualifier("bookDao2")
    private BookDao bookDao;

    @Override
    public void save() {
        System.out.println("bookService save book");
        bookDao.save();
    }

    @Override
    public void update() {
        System.out.println("bookService update book");
    }
}
