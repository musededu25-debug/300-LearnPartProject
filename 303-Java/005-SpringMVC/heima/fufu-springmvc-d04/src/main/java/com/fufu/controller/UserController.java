package com.fufu.controller;

import com.fufu.domain.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 第一步pom里面添加相关依赖
 * 第二部创建用户控制器
 */
@Controller
public class UserController {

    @RequestMapping(value = "/users",method = RequestMethod.POST)
    @ResponseBody
    public String save(){
        System.out.println("user save");
        return "user save";
    }

    @RequestMapping(value = "/users/{id}",method =  RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable Integer id){
        System.out.println("user delete" + id);
        return "user delete";
    }

    @RequestMapping(value = "/users",method =  RequestMethod.PUT)
    @ResponseBody
    public String update(){
        System.out.println("user update");
        return "user update";
    }

}
