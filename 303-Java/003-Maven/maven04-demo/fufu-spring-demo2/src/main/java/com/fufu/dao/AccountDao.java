package com.fufu.dao;

/**
 * 持久层（DAO = Data Access Object）接口。
 *
 * 【为什么要先写接口，而不是直接写实现类？】
 * 这是 Spring 解耦的核心思想：Service 层只依赖"接口"这个抽象，
 * 而不依赖"实现"这个具体类。将来换一套数据库实现（比如换成 JPA 的 AccountDaoImpl），
 * 只要改 XML 配置，AccountServiceImpl 的代码一行都不用动。
 *
 * Spring 面向接口编程的三大好处：
 *   1. 解耦：调用方不需要知道具体实现是谁
 *   2. 可替换：换实现只改配置，不改 Java 代码
 *   3. 可代理：Spring 可以基于接口生成代理对象，实现事务、日志等增强（后面 AOP 会学）
 */
public interface AccountDao {

    /**
     * 保存一个账户。
     * 接口里只声明"要做什么"，不关心"怎么做"。
     */
    public void addAccount();
}
