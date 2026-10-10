package com.fufu.dao.impl;

import com.fufu.dao.AccountDao;

/**
 * AccountDao 的【第二套实现】—— 专门用来演示"换实现只改 XML 一行"。
 *
 * 【为什么这里不写 JDBC？】
 * 因为此刻要学的知识点是 Spring 的 IoC / 依赖注入，不是数据库。
 * 真实的 JDBC 代码大概长这样（你现在看不懂完全正常，以后会专门学）：
 *
 *     DataSource ds = ...;
 *     try (Connection conn = ds.getConnection();
 *          PreparedStatement ps = conn.prepareStatement("insert into account ...")) {
 *         ps.setString(1, name);
 *         ps.executeUpdate();
 *     }
 *
 * 这些 Connection / PreparedStatement / SQL 会占满整个屏幕，
 * 反而把"Spring 到底把哪个对象注入给了谁"这个重点淹没了。
 *
 * 【关键认知 —— 这句最重要】
 * 实现类的方法体里写什么，Spring 一点都不关心。它只关心两件事：
 *   1. 这个类能不能被 new 出来（有没有无参构造）
 *   2. 它有没有 implements AccountDao（能不能塞进那个位置）
 *
 * 至于方法体里是一句打印、还是连数据库、还是调远程接口 —— 与 Spring 无关。
 * 所以下面用一句打印代替，学习效果完全等价，但你能一眼看懂。
 */
public class AccountDaoImpl2 implements AccountDao {

    /**
     * 注意：方法签名和 AccountDaoImpl 一模一样。
     *
     * 这不是巧合 —— 接口 AccountDao 就是双方签的"合同"，
     * 谁签了这份合同，就必须提供 addAccount() 这个方法，
     * 也必须能被当作 AccountDao 使用。
     *
     * 正因为签名一致，AccountServiceImpl 才能毫无察觉地被"换掉"底层实现：
     * 它调用的永远是 accountDao.addAccount()，
     * 至于最终跑到哪个类的这个方法里，由 XML 决定。
     */
    @Override
    public void addAccount() {
        System.out.println("【第 2 套实现】账户已保存（这里将来会是真正的数据库操作）");
    }
}
