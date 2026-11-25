package com.fufu.boot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 方式 1：@ConfigurationProperties + Java 21 record（实际业务成组配置最推荐）。
 *
 * <p>将 application.yml 中 enterprise.* 下的整组配置自动打包绑定到本对象。
 * record 天然不可变（底层自动生成 private final 字段 + 全参构造器 + name()/age() 等取值方法）。
 */
@ConfigurationProperties(prefix = "enterprise")
public record EnterpriseProperties(
        String name,
        int age,
        String tel,
        List<String> subjects
) {}
