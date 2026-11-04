package com.fufu.proxy;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxyFactory {

    // 使用泛型，传入什么目标类，就返回什么类型的代理对象
    @SuppressWarnings("unchecked")
    public static <T> T createProxy(Class<T> targetClass) {
        return (T) Enhancer.create(targetClass, new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("[日志] 方法 " + method.getName() + " 开始执行...");
                try {
                    // 执行父类（目标类）原方法
                    return methodProxy.invokeSuper(o, objects);
                } finally {
                    System.out.println("[日志] 方法 " + method.getName() + " 执行结束。");
                }
            }
        });
    }

//    public static ProduceDao createProxy() {
//        Enhancer enhancer = new Enhancer();
//        enhancer.setSuperclass(ProduceDaoImpl.class); // 父类仍是具体实现
//        enhancer.setCallback(new MethodInterceptor() {
//            @Override
//            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
//                System.out.println("[CGLIB 前置]");
//                Object result = methodProxy.invokeSuper(proxy, args);
//                System.out.println("[CGLIB 后置]");
//                return result;
//            }
//        });
//        return (ProduceDao) enhancer.create(); // 返回接口，降低耦合 ✔
//    }

    /**
     *
     * // 1. 定义过滤器
     * CallbackFilter filter = new CallbackFilter() {
     *     @Override
     *     public int accept(Method method) {
     *         if ("buyProduce".equals(method.getName())) {
     *             return 0; // 返回 0，表示使用第 0 个拦截器（增强）
     *         }
     *         return 1; // 返回 1，表示使用第 1 个拦截器（不增强，直接放行）
     *     }
     * };
     *
     * // 2. 设置给 Enhancer
     * Enhancer enhancer = new Enhancer();
     * enhancer.setSuperclass(ProduceDaoImpl.class);
     * enhancer.setCallbackFilter(filter); // 设置过滤器
     *
     * // 3. 设置回调数组（顺序对应 accept 返回的编号）
     * enhancer.setCallbacks(new Callback[]{
     *     new MethodInterceptor() { // 拦截器 0：包含增强逻辑
     *         @Override
     *         public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
     *             System.out.println("[前置增强]");
     *             Object res = proxy.invokeSuper(obj, args);
     *             System.out.println("[后置增强]");
     *             return res;
     *         }
     *     },
     *     new MethodInterceptor() { // 拦截器 1：什么都不做，直接放行
     *         @Override
     *         public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
     *             return proxy.invokeSuper(obj, args);
     *         }
     *     }
     * });
     *
     * ProduceDaoImpl proxy = (ProduceDaoImpl) enhancer.create();
     */
}