package com.fufu.proxy;

import com.fufu.dao.ProduceDao;
import com.fufu.dao.impl.ProduceDaoImpl;
import org.junit.jupiter.api.Test;

public class CglibProxyTest {

    /**
     * 测试方式一：CGLIB 的典型场景 —— 代理对象作为目标类的子类（返回类类型）
     * 适合：目标类没有实现任何接口，或者在框架内部直接通过类代理。
     */
    @Test
    public void testCglibProxyWithClass() {
        // 直接生成代理对象，注意这里不需要 new ProduceDaoImpl()
        ProduceDaoImpl proxy = CglibProxyFactory.createProxy(ProduceDaoImpl.class);

        System.out.println("------ 开始调用 CGLIB 代理（返回类） ------");
        proxy.buyProduce("张三");
    }

    /**
     * 测试方式二：面向接口编程 —— 代理对象返回接口类型（降低耦合）
     * 适合：业务代码依赖接口，屏蔽底层代理的具体实现。
     */
    @Test
    public void testCglibProxyWithInterface() {
        // 1. 告诉工厂，我要代理 ProduceDaoImpl 这个类
        // 2. 由于代理对象既是 ProduceDaoImpl 的子类，也实现了 ProduceDao 接口，所以我们可以直接用接口接收
        ProduceDao proxy = CglibProxyFactory.createProxy(ProduceDaoImpl.class);

        System.out.println("------ 开始调用 CGLIB 代理（返回接口） ------");
        proxy.buyProduce("李四");
    }
}