package com.fufu.boot.controller;

import com.fufu.boot.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 第一个 REST 接口：GET /hello?name=xxx
 *
 * <p>@RestController = @Controller + @ResponseBody，
 * 方法返回值直接作为响应体写出（这里是纯文本，返回对象时会自动转 JSON）。
 *
 * <p>依赖通过构造器注入：由 Spring 创建 HelloService 并传进来，
 * Controller 内部不 new，这也是 Spring 官方推荐的做法。
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping
    public String hello(@RequestParam(defaultValue = "Spring Boot") String name) {
        return helloService.greet(name);
    }
}
