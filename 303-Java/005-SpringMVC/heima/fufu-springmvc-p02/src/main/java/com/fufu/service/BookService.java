package com.fufu.service;

import com.fufu.pojo.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 图书业务层
 *
 * 现在还没有数据库，这里的 List<Book> 就相当于一张 book 表：
 *   books.add(...)      ≈ insert
 *   books.remove(...)   ≈ delete
 *   遍历找 id            ≈ select ... where id = ?
 *   return books        ≈ select *
 *
 * 下一章学完 MyBatis 后，方法体里这些操作会换成一行 mapper 调用，
 * Controller 完全不用动 —— 这就是把逻辑放在 Service 里的好处。
 */
@Service
public class BookService {

    /** 假装是数据库表 */
    private final List<Book> books = new ArrayList<>();

    /** 自增主键（数据库里对应 auto_increment） */
    private final AtomicInteger seq = new AtomicInteger(0);

    /** 查询所有 */
    public List<Book> list() {
        return books;
    }

    /** 按 id 查询：找不到返回 null */
    public Book getById(Integer id) {
        for (Book book : books) {
            if (id.equals(book.getId())) {
                return book;
            }
        }
        return null;
    }

    /** 新增：分配 id 后放进列表 */
    public Book save(Book book) {
        book.setId(seq.incrementAndGet());
        books.add(book);
        return book;
    }

    /** 修改：先找到原对象，再把新值 set 进去 */
    public Book update(Integer id, Book book) {
        Book old = getById(id);
        if (old == null) {
            return null;
        }
        old.setName(book.getName());
        old.setPrice(book.getPrice());
        return old;
    }

    /** 删除：成功返回 true */
    public boolean delete(Integer id) {
        return books.removeIf(book -> id.equals(book.getId()));
    }
}
