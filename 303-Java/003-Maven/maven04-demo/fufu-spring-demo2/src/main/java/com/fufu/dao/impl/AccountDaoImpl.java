package com.fufu.dao.impl;

import com.fufu.dao.AccountDao;

/**
 * AccountDao 接口的实现类，也就是真正干活的"持久层实现"。
 *
 * 【这个类不需要任何 Spring 注解或继承】
 * Spring 的 XML 配置方式是非侵入式的：
 * 这个类就是一个普普通通的 Java 类（POJO），
 * 是 applicationContext.xml 里的 <bean id="accountDao" class="...AccountDaoImpl"/>
 * 告诉 Spring："把这个类 new 出来，交给容器管理"。
 *
 * 对比：如果是注解方式，这里会写 @Repository，那才叫侵入式。
 */
public class AccountDaoImpl implements AccountDao {

    /**
     * 实现接口中的 addAccount 方法。
     *
     * 真实项目里这里应该是 JDBC / MyBatis / JPA 的数据库操作，
     * 学习阶段用一句打印代替，只要能看到方法被调用到就说明注入成功了。
     */
    @Override
    public void addAccount() {
        System.out.println("add account success!");
    }
}
