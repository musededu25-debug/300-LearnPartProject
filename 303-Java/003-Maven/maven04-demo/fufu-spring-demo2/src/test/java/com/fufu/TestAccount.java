package com.fufu;

import com.fufu.service.AccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试类：验证 Spring 的 IoC + 依赖注入是否正常工作。
 */
public class TestAccount {

    @Test
    public void test01(){

        /*
         * 第 1 步：启动 Spring 容器。
         *
         * 这行代码执行时，Spring 会：
         *   1. 读取 classpath 下的 applicationContext.xml
         *   2. 解析里面所有的 <bean> 标签
         *   3. 用反射把每个 Bean 都 new 出来（accountDao、accountService）
         *   4. 处理依赖注入：把 accountDao 塞进 accountService
         *
         * 注意"第 3 步"里就包含了 new AccountServiceImpl()，
         * 所以之前那个 "-No default constructor found" 的报错
         * 是【这一行】抛出来的，不是你调用 add() 的时候才抛的。
         * 异常堆栈里能看到 TestAccount.test01(TestAccount.java:12) 就是证据。
         *
         * ClassPathXmlApplicationContext = 从类路径加载 XML 配置的容器实现。
         */
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        /*
         * 第 2 步：从容器里取出 Bean。
         *
         * getBean("accountService") 里的字符串，就是 XML 中 <bean id="..."> 的值。
         *
         * 返回类型是 Object，所以要强转。为什么能转成 AccountService？
         * 因为 XML 里配的 class 是 AccountServiceImpl，
         * 而 AccountServiceImpl implements AccountService，是它的子类型。
         *
         * 【为什么不直接写 AccountServiceImpl？】
         * 因为这个测试代码也遵循"面向接口"原则：
         * 它拿到的是接口类型，不需要知道底层到底是哪个实现类。
         *
         * getBean 还有重载写法：context.getBean(AccountService.class)，
         * 按类型取，就不用强转了 —— 容器里该类型只有一个 Bean 时才能这么写。
         */
        AccountService accountService = (AccountService) context.getBean("accountService");

        /*
         * 第 3 步：调用业务方法。
         *
         * 预期输出：add account success!
         *
         * 这句话是 AccountDaoImpl 里的打印，而它是由 AccountServiceImpl
         * 间接调用的，说明：容器创建对象 ✓ → setter 注入成功 ✓ → 业务链路打通 ✓
         *
         * 如果注入没成功，accountDao 会是 null，
         * 走到 AccountServiceImpl.add() 里的 accountDao.addAccount() 时报
         * NullPointerException —— 这也是判断"注入成功与否"的一个直观信号。
         */
        accountService.add();
    }
}
