package com.fufu.boot.controller;

import com.fufu.boot.config.EnterpriseProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 演示两种最常用的配置读取方式：
 * 1. 成组配置：注入 @ConfigurationProperties 绑定的 EnterpriseProperties（推荐）
 * 2. 零散单值：@Value("${key:default}") 读取单个配置项
 */
@RestController
@RequestMapping("/info")
public class InfoController {

    private final EnterpriseProperties enterprise;
    private final String appName;

    public InfoController(
            EnterpriseProperties enterprise,
            @Value("${spring.application.name:default-app}") String appName) {
        this.enterprise = enterprise;
        this.appName = appName;
    }

    @GetMapping
    public String info() {
        return appName + " -> " + enterprise.name()
                + "（成立 " + enterprise.age() + " 年，课程：" + enterprise.subjects() + "）";
    }
}
