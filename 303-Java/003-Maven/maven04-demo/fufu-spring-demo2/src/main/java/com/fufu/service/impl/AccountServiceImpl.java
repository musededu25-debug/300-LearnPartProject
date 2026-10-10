package com.fufu.service.impl;

import com.fufu.dao.AccountDao;
import com.fufu.service.AccountService;

/**
 * 业务层实现类 —— 这个类是被 Spring 容器管理的 Bean。
 *
 * 【★ 这里就是之前测试失败的地方 ★】
 * 原来这个类有一个带参构造方法：
 *     public AccountServiceImpl(AccountDao accountDao) { ... }
 *
 * Java 语法规则：一旦你手写了任意一个构造方法，
 * 编译器就不再自动生成默认的无参构造方法 AccountServiceImpl()。
 *
 * 而 XML 里写的是 <property name="accountDao" ref="accountDao"/>，
 * 这属于【setter 注入】，Spring 的执行顺序是：
 *     第 1 步：new AccountServiceImpl()   ← 需要无参构造，这里就炸了
 *     第 2 步：调用 setAccountDao(accountDao) 把依赖塞进去
 *
 * 第 1 步找不到无参构造，于是报：
 *     BeanInstantiationException: No default constructor found
 *     Caused by: NoSuchMethodException: AccountServiceImpl.<init>()
 *
 * 【结论】setter 注入 和 带参构造 二选一，不能同时存在：
 *   - 用 <property>（setter 注入）→ 类里必须保留【无参构造】+ setter
 *   - 用 <constructor-arg>（构造注入）→ 类里写带参构造，可以不写 setter
 *
 * 所以这里删掉了带参构造，只保留 setter，与 XML 的写法保持一致。
 */
public class AccountServiceImpl implements AccountService {

    /**
     * 依赖对象，类型是【接口】AccountDao，不是实现类 AccountDaoImpl。
     *
     * 这一行是 Spring 解耦的体现：本类不知道、也不关心
     * 具体用的是哪个实现，那个对象是 Spring 在运行时"注入"进来的。
     *
     * 注意：这里不需要写 = new AccountDaoImpl();
     * 控制权已经从"我自己 new"反转给了"容器帮我 new"——
     * 这就是"控制反转（IoC）"这个名字的由来。
     */
    private AccountDao accountDao;

    /**
     * 无参构造方法。
     *
     * 其实删掉带参构造后，编译器会自动生成一个一模一样的无参构造，
     * 所以这个构造方法【可以省略不写】。这里显式写出来，
     * 是为了让你看清楚 "Spring 实例化 Bean 时用的就是它"。
     *
     * 一旦你以后再加一个带参构造，这个无参构造千万不能删，
     * 否则又会退回到刚才那个报错。
     */
    public AccountServiceImpl() {
        // Spring 调用这个方法创建对象，此时 accountDao 还是 null
    }

    /**
     * setter 方法 —— Spring 靠它完成【依赖注入】。
     *
     * XML 里的 ref="accountDao" 就是告诉 Spring：
     *   把容器里 id 为 accountDao 的那个 Bean 对象，
     *   作为参数传进来调用这个方法。
     *
     * 注意方法名必须是 setXxx，Spring 才能根据
     * <property name="xxx"> 反推出对应的 setter（JavaBean 命名规范）：
     *   <property name="accountDao"> → setAccountDao(...)
     * 名字对不上，Spring 就会报 "Bean property 'xxx' is not writable"。
     */
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    /**
     * 业务方法实现。
     *
     * 这里直接调用 accountDao.addAccount()，说明注入成功了 ——
     * 如果注入失败，accountDao 会是 null，走到这行就抛 NullPointerException。
     */
    @Override
    public void add() {
        accountDao.addAccount();
    }
}
