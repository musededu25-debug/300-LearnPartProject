package com.fufu.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(value = "com.fufu")
@Import(value = {DataSourceConfiguration.class})
public class SpringConfiguration {

}