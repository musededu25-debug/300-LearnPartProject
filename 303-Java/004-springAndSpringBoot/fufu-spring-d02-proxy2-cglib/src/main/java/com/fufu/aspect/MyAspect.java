package com.fufu.aspect;

/**
 *
 * @author M <musededu25@gmail.com>
 * @since 2026/11/04 23:27
 */

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 切面类
 */

public class MyAspect {

    //权限校验,想要增强delectUser方法--->前置通知
    public void checkPrivilege(){
        System.out.println("开启了权限校验");
    }

    //打印日志方法---->后置通知
    public void printLog(){
        System.out.println("记录日志");
    }

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
