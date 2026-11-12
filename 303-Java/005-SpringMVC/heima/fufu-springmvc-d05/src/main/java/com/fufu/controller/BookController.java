package com.fufu.controller;

import com.fufu.pojo.Book;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    //增
    @PostMapping
    public String save(@RequestBody Book book) {
        System.out.println("book save..." + book);
        return "book save";
    }

    //删
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        System.out.println("book delete..." + id);
        return "book delete";
    }

    //改
    @PutMapping("/{id}")
    public String update(@PathVariable Integer id, @RequestBody Book book) {
        System.out.println("book update..." + id);
        return "book update";
    }

    //查
    @GetMapping("/{id}")
    public String get(@PathVariable Integer id) {
        System.out.println("book get..." + id);
        return "book get";
    }

}
