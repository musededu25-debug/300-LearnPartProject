package com.fufu.boot;

import com.fufu.boot.config.EnterpriseProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Spring Boot 应用启动类。
 *
 * <p>@SpringBootApplication 是一个组合注解，等价于：
 * <ul>
 *   <li>@SpringBootConfiguration：声明这是一个配置类</li>
 *   <li>@EnableAutoConfiguration：开启自动配置（Boot 的核心）</li>
 *   <li>@ComponentScan：扫描「本类所在包及其子包」下的组件</li>
 * </ul>
 *
 * <p>因此启动类必须放在最外层的根包里（这里是 com.fufu.boot），
 * controller / service / mapper 等都要在它的子包下，否则扫不到。
 */
@SpringBootApplication
@EnableConfigurationProperties(EnterpriseProperties.class)
public class FufuBootDemo1Application {

    public static void main(String[] args) {
        SpringApplication.run(FufuBootDemo1Application.class, args);
    }
}
