package com.fufu.boot.controller;

import com.fufu.boot.config.StudentProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class ProfileController {

    private final StudentProperties studentProperties;
    private final String appName;

    public ProfileController(StudentProperties studentProperties,
                             @Value("{spring.application.name:default-app}") String appName) {
        this.studentProperties = studentProperties;
        this.appName = appName;
    }

    @GetMapping
    public String info() {
        return appName + " -> " + studentProperties.name()
                + " -> " + studentProperties.studyDays()
                + " -> " + studentProperties.targetRole()
                + " -> " + studentProperties.skills();
    }
 }
