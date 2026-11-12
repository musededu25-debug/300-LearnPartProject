package com.fufu.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan({"com.fufu.controller",
        "com.fufu.config"})
//下面这个千万不要忘记了
@EnableWebMvc
public class SpringMvcConfig {
}
