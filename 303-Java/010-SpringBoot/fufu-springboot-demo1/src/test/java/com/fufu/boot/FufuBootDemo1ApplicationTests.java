package com.fufu.boot;

import com.fufu.boot.service.HelloService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 启动上下文测试。
 *
 * <p>@SpringBootTest 会真正启动 Spring 容器（默认不启动 Web 服务器），
 * 因此能验证「自动配置 + 包扫描 + 依赖注入」整条链路是否通。
 */
@SpringBootTest
class FufuBootDemo1ApplicationTests {

    @Autowired
    private HelloService helloService;

    @Test
    @DisplayName("Spring 容器能正常启动，并且能注入 HelloService")
    void contextLoads() {
        assertEquals("Hello, Spring Boot! 来自 Spring Boot 4", helloService.greet("Spring Boot"));
    }
}
