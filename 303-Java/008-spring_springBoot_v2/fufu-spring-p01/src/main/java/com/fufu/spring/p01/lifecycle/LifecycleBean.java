package com.fufu.spring.p01.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class LifecycleBean {

    public LifecycleBean() {
        System.out.println("构造器完成");
    }

    @PostConstruct
    public void init() {
        System.out.println("执行@PostConstruct");
    }

    public void use(){
        System.out.println("执行普通方法use()");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("执行@PreDestroy");
    }
}
