package com.fufu.aspect;

/**
 *
 * @author M <musededu25@gmail.com>
 * @since 2026/11/04 23:27
 */

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 切面类
 */
@Component
@Aspect
public class MyAspect {

    @Pointcut(value = "execution(* com.fufu.service.UserService.delectUser(..))")
    public void p1(){

    }


    //@Before(value = "execution(* com.fufu.service.UserService.delectUser(..))")
    @Before(value = "p1()")
    //权限校验,想要增强delectUser方法--->前置通知
    public void checkPrivilege(){
        System.out.println("开启了权限校验");
    }

    @AfterReturning( value = "execution(* com.fufu.service.UserService.updateUser(..))")
    //打印日志方法---->后置通知
    public void printLog(){
        System.out.println("记录日志");
    }

    @Around( value = "execution(* com.fufu.service.UserService.findAll(..))")
    //环绕通知
    public void around(ProceedingJoinPoint proceedingJoinPoint){
        try {
            System.out.println("开启前置通知");
            proceedingJoinPoint.proceed();
            System.out.println("开启了后置通知");
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }



}
