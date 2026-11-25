package com.fufu.boot;

import com.fufu.boot.config.EnterpriseProperties;
import com.fufu.boot.controller.InfoController;
import com.fufu.boot.service.HelloService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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

    @Autowired
    private EnterpriseProperties enterprise;

    @Autowired
    private InfoController infoController;

    @Test
    @DisplayName("Spring 容器能正常启动，并且能注入 HelloService")
    void contextLoads() {
        assertEquals("Hello, Spring Boot! 来自 Spring Boot 4", helloService.greet("Spring Boot"));
    }

    @Test
    @DisplayName("方式 1：YAML 中的 enterprise.* 成功绑定到 EnterpriseProperties record")
    void enterprisePropertiesBindingWorks() {
        System.out.println("绑定结果 => " + enterprise);
        System.out.println("/info 输出 => " + infoController.info());

        assertEquals("fufu-tech", enterprise.name());
        assertEquals(6, enterprise.age());
        assertEquals("400-618-4000", enterprise.tel());
        assertEquals(List.of("Java", "Spring Boot", "MySQL"), enterprise.subjects());
        assertEquals(
                "fufu-springboot-demo1 -> fufu-tech（成立 6 年，课程：[Java, Spring Boot, MySQL]）",
                infoController.info()
        );
    }
}
