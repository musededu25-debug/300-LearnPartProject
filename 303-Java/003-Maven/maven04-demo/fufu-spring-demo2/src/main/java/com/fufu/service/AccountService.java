package com.fufu.service;

/**
 * 业务层（Service）接口 —— 对外提供"业务功能"。
 *
 * 【分层结构】
 *   Controller（控制层，接收请求）
 *        ↓ 依赖
 *   Service（业务层，写业务逻辑：校验、组装、调用多个 DAO）
 *        ↓ 依赖
 *   Dao（持久层，只跟数据库打交道）
 *
 * 每一层都"面向接口"编程，层与层之间靠接口连接，
 * 这样上层就不会被下层的实现细节绑死。
 */
public interface AccountService {

    /**
     * 新增账户的业务方法。
     *
     * 注意它和 AccountDao.addAccount() 的区别：
     *   add()          —— 业务动作。真实场景里可能包含"参数校验 + 调用多个 DAO + 发通知"
     *   addAccount()   —— 纯粹的数据库动作
     * 名字相近但不是一回事，不要混。
     */
    public void add();

}
