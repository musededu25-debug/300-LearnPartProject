package com.fufu.controller;

import com.fufu.domain.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * 第一步pom里面添加相关依赖
 * 第二部创建用户控制器
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/list")
    @ResponseBody
    public String list(){
        return "user/list";
    }

    @RequestMapping("/detail")
    @ResponseBody
    public String detailed(){
        return "user/detailed";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(){
        return "user/delete";
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test01(@RequestParam("name") String name , int age){
        System.out.println("普通参数传递 name=====>" + name);
        System.out.println("普通参数传递 age=====>" + age);
        return "user/test01";
    }

    @RequestMapping("/test02")
    @ResponseBody
    public String test02(@RequestParam("likes") List<String> likes){
        System.out.println("集合参数传递 like=====>" + likes);
        return "user/test02";
    }

    @RequestMapping("/test03")
    @ResponseBody
    public String test03(@RequestBody List<String> likes){
        System.out.println("list====>" + likes);
        return "user/test03";
    }

    @RequestMapping("/test04")
    @ResponseBody
    public String test04(@RequestBody User user){
        System.out.println("user ====>" + user);
        return "user/test03";
    }

    @RequestMapping("/test05")
    @ResponseBody
    public String test05(@RequestBody List<User> list){
        System.out.println("list ====>" + list);
        return "user/test03";
    }

    //已学习未实现

    //时间格式@DatetimeFormat(pattern = "") Date date;
    @RequestMapping("/test06")
    @ResponseBody
    public String test06(@DateTimeFormat(pattern = "yyyy/MM/dd") Date date){
        System.out.println(date);
        return "user/test06";
    }

    //响应json数据
    @RequestMapping("/test07")
    @ResponseBody
    public User test07(User user){
        user.setAge(18);
        user.setName("zhangsan");
        System.out.println("user ===>" + user);
        return user;
    }

}
