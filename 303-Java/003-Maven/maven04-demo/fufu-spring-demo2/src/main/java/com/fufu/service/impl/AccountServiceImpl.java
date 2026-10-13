package com.fufu.service.impl;

import com.fufu.dao.AccountDao;
import com.fufu.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

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
@Service(value = "accountService")
@Scope(value = "singleton")
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
    @Autowired
    @Qualifier("accountDao2")
    AccountDao accountDao;

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
     * ══════════════════════════════════════════════════════════
     * 【从 XML 切到注解，这一步是必须补上的】
     *
     * 你之前 XML 里写的是：
     *     <property name="accountDao" ref="accountDao"/>
     * 那一行本身就是"注射指令" —— 它告诉 Spring "调用 setAccountDao 并传入 accountDao"。
     *
     * 现在你把手写的 <bean> 全注释掉了，改成 <context:component-scan> 自动扫描。
     * 扫描只能发现"类"，它【不知道】哪些属性需要被注入：
     *     @Service  = "这个类归你管，帮我 new 出来"
     *     但没写 @Autowired 的话，Spring 只会 new 出对象，
     *     然后 setAccountDao 这个方法【根本不会被调用】，accountDao 就一直是 null。
     *
     * 结果：启动不报错，但一执行 add() 里的 accountDao.addAccount() 就抛
     *     NullPointerException
     * —— 比启动报错更难查，因为异常位置和真正的原因（漏了 @Autowired）隔得很远。
     *
     * 所以注解模式下，注入点必须显式标记 @Autowired。
     * ══════════════════════════════════════════════════════════
     *
     * 【@Autowired 是怎么找对象的？】
     * 默认【按类型找】：容器里谁是 AccountDao 类型，就注入谁。
     * 但你现在有两个实现类（accountDao、accountDao2），类型都是 AccountDao，
     * Spring 找到两个候选，就会抛 NoUniqueBeanDefinitionException（不唯一）。
     *
     * 所以要用 @Qualifier("accountDao") 明确指定"我要名字叫 accountDao 的那个"。
     *
     * 【一个容易忽略的细节】
     * @Qualifier 写在【参数】上而不是方法上 —— 方法只有一个参数时两者效果一样，
     * 但参数位置是更规范、更不容易出错的写法（多参数时必须写在参数上）。
     *
     * 【顺带一提】如果容器里只有一个 AccountDao，@Autowired 可以省略 @Qualifier；
     * 甚至字段名恰好等于 Bean 名字时 Spring 也会兜底按名字匹配。
     * 但那种"隐式生效"很脆弱 —— 以后多来一个实现类就悄悄换人了，
     * 所以初学者建议：只要有多实现，就老老实实写 @Qualifier。
     */
//    @Autowired
//    public void setAccountDao(@Qualifier("accountDao") AccountDao accountDao) {
//        this.accountDao = accountDao;
//    }

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
