package com.fufu.demo3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyUtil {

    public static Star creatProxy(BigStar star){
        Star starProxy = (Star) Proxy.newProxyInstance(ProxyUtil.class.getClassLoader(),
                new Class[]{Star.class}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if(method.getName().equals("sing")){
                            System.out.println("经理收取500块钱的门票");
                        }else if(method.getName().equals("dance")){
                            System.out.println("经理购买场地");
                        }
                        return method.invoke(star,args);
                    }
                });
        return starProxy;
    }
}
