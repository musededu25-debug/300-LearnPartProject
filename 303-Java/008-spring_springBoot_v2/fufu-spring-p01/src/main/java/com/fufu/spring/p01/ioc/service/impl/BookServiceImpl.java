package com.fufu.spring.p01.ioc.service.impl;

import com.fufu.spring.p01.ioc.dao.BookDao;
import com.fufu.spring.p01.ioc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {


    private final BookDao bookDao;

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public void save(String bookName) {
        bookDao.save(bookName);
    }
}
