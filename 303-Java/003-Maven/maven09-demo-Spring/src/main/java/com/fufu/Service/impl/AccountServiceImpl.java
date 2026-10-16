package com.fufu.Service.impl;

import com.fufu.Service.AccountService;
import com.fufu.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @Service 把这个类注册进 Spring 容器。
 * value = "accountService" 就是 Bean 的 id，等价于原来 XML 里的
 * <bean id="accountService" class="com.fufu.Service.impl.AccountServiceImpl">
 */
@Service(value = "accountService")
public class AccountServiceImpl implements AccountService {

    /**
     * 【修复】改用注解注入，把 Service -> DAO 的依赖链接回来。
     *
     * 原来这里是靠 applicationContext.xml 里的
     * <property name="accountDao" ref="accountDao"/> + setAccessor 注入的，
     * 换成 <context:component-scan> 之后，XML 里那两个 <bean> 已经被删掉，
     * 那条注入路径也就不存在了，必须换成 @Autowired。
     *
     * @Autowired 默认按【类型】匹配：容器里实现 AccountDao 接口的只有
     * AccountDaoImpl 一个，所以直接就能匹配上，不需要写 Bean 的 id。
     * （若同类型有多个实现，才需要配合 @Qualifier("accountDao") 指定名字）
     *
     * 注意：@Autowired 不生效的前提是容器开启了注解处理。
     * <context:component-scan> 已经隐含了 <context:annotation-config/> 的作用，
     * 所以不用再额外配一行。
     */
    @Autowired
    @Qualifier(value = "accountDao")
    private AccountDao accountDao;


    @Override
    public void add() {
        /*
         * 【修复】把注释掉的调用恢复回来，让 Service 真正委托给 DAO。
         * 之前这里只打印一行日志，虽然测试是绿的，但压根没走到 DAO 层，
         * 属于"假通过"。
         */
        accountDao.addAccount();
    }
}
