package com.fufu.controller;

import com.fufu.pojo.Book;
import com.fufu.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 图书接口（REST 风格）
 *
 * 注意两个注解的分工：
 *   @RestController  —— 告诉 Spring：这个类的返回值直接写进响应体（不用再写 @ResponseBody）
 *   @RequestMapping  —— 设置路径前缀，参数才是路径
 *                       写错成 @RestController("/books") 是不行的，那是 bean 名称
 */
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // 查所有
    @GetMapping
    public List<Book> list() {
        return bookService.list();
    }

    // 查单个
    @GetMapping("/{id}")
    public Book get(@PathVariable Integer id) {
        return bookService.getById(id);
    }

    // 增
    @PostMapping
    public Book save(@RequestBody Book book) {
        return bookService.save(book);
    }

    // 改
    @PutMapping("/{id}")
    public Book update(@PathVariable Integer id, @RequestBody Book book) {
        return bookService.update(id, book);
    }

    // 删（返回纯文本，用英文避免乱码：String 返回值默认不按 UTF-8 编码）
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        return bookService.delete(id) ? "deleted " + id : "not found " + id;
    }
}
