package com.fufu.boot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "student")
public record StudentProperties(
    String name,
    int[] studyDays,
    List<String> targetRole,
    List<String> skills
) {}
