package com.fufu.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//把控制器交给Spring容器管理
@Configuration
@ComponentScan("com.fufu.controller")
public class SpringMvcConfig {


}
