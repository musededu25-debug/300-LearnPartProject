package com.fufu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//图书控制器
@Controller
public class BookController {

    @RequestMapping("book/list")
    @ResponseBody
    public String list(){
        return "list";
    }
}
