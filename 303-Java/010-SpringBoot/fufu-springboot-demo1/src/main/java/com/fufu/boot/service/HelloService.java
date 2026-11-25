package com.fufu.boot.service;

import org.springframework.stereotype.Service;

/**
 * 业务层示例：演示「controller → service」的分层调用。
 *
 * <p>真实项目中这一层负责业务规则、事务边界和多个 Dao/Mapper 的编排。
 * 当前还没有数据库，所以先放一个纯计算的方法。
 */
@Service
public class HelloService {

    public String greet(String name) {
        return "Hello, " + name + "! 来自 Spring Boot 4";
    }
}
