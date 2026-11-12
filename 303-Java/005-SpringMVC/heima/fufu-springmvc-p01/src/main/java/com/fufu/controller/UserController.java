package com.fufu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//创建springMvc控制类
@Controller
public class UserController {

    //用户列表
    @RequestMapping("/user/list")

    public String list() {
        //return "userList";
        return "/index.jsp";
    }

    @RequestMapping("/user/detail")
    @ResponseBody
    public String detail(){
        return "userDetail";
    }

    //删除用户
    @RequestMapping("/user/delete")
    @ResponseBody
    public String delete() {
        return "userDelete";
    }
}
