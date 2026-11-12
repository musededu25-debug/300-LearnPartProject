package com.fufu.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * SpringMVC 容器配置
 *
 * @EnableWebMvc —— 启用 SpringMVC 的注解驱动（@RequestMapping 等才能生效）
 *                 它是"配置类"上的注解，不要写到 Controller 上
 *
 * @ComponentScan —— 扫描范围。控制器、配置类、Service 都要在范围内，
 *                   否则类上写了 @Controller / @Service 也不会变成 bean
 */
@Configuration
@ComponentScan({"com.fufu.controller",
        "com.fufu.service",
        "com.fufu.config"})
@EnableWebMvc
public class SpringMvcConfig {
}
