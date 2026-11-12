package com.fufu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
//2.定义controller
//2.1使用@Controller定义bean
@Controller
public class UserController {

    //设置访问路径
    @RequestMapping("/save")
    //设置当前操作的返回值
    @ResponseBody
    public String save(){
        System.out.println("user save ...");
        return "{'module':'springMvc'}";
    }

}
