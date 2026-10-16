package com.fufu.dao;

import com.fufu.pojo.Account;
import java.util.List;

public interface AccountDao {
    /**
     * 查询所有账户
     */
    List<Account> findAllAccount();

    /**
     * 根据 ID 查询账户
     */
    Account findAccountById(Integer accountId);

    /**
     * 保存账户
     */
    void saveAccount(Account account);

    /**
     * 更新账户
     */
    void updateAccount(Account account);

    /**
     * 根据 ID 删除账户
     */
    void deleteAccount(Integer accountId);
}