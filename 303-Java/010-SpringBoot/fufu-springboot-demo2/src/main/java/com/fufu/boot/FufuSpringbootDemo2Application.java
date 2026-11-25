package com.fufu.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class FufuSpringbootDemo2Application {

    public static void main(String[] args) {
        SpringApplication.run(FufuSpringbootDemo2Application.class, args);
    }

}
