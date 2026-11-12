package com.fufu.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

@Configuration
//精切扫包范围
//@ComponentScan({"com.fufu.service",
//        "com.fufu.dao",
//        "com.fufu.domain"})

//排查包，主要还是方式一
@ComponentScan(value = "com.fufu" ,
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                classes = Controller.class
        ))
public class SpringConfig {
}
