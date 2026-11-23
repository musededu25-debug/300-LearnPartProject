package com.fufu.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

//交给spring处理
@Component
//告诉Spring这是aop
@Aspect
public class MyAdvice {

    @Pointcut("execution(void com.fufu.spring.dao.BookDao.save())")
    private void pt(){}

    @Before("pt()")
    public void method(){
        System.out.println(System.currentTimeMillis());
    }
}
