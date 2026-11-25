package com.fufu.spring.p01.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * p01 巩固练习的 Spring 容器入口配置。
 *
 * <p>练习时把带 {@code @Component} / {@code @Repository} / {@code @Service} 的类
 * 放在 {@code com.fufu.spring.p01} 包及其子包下，就能被这里扫描到。</p>
 */
@Configuration
@ComponentScan("com.fufu.spring.p01")
public class P01Config {
}
