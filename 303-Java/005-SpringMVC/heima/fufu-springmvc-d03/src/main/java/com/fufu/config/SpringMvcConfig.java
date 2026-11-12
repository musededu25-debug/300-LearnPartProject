package com.fufu.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 第三部将控制器交给Spring容器管理
 */
@Configuration
@ComponentScan({"com.fufu.controller"})
//开启json转换对象
@EnableWebMvc
public class SpringMvcConfig {
}
