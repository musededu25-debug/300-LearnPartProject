package com.fufu.service;

import com.fufu.pojo.Account;
import java.util.List;

public interface AccountService {

    /**
     * 查询所有账户信息
     * @return 账户集合
     */
    List<Account> findAllAccount();

    /**
     * 查询指定的账户信息
     * @param accountId 账户ID
     * @return 账户对象
     */
    Account findAccountById(Integer accountId);

    /**
     * 保存账户信息
     * @param account 账户对象
     */
    void saveAccount(Account account);

    /**
     * 更新账户信息
     * @param account 账户对象
     */
    void updateAccount(Account account);

    /**
     * 删除账户信息
     * @param accountId 账户ID
     */
    void deleteAccount(Integer accountId);
}