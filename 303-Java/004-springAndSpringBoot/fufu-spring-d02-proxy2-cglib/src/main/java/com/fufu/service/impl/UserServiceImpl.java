package com.fufu.service.impl;

import com.fufu.service.UserService;

/**
 *
 * @author M <musededu25@gmail.com>
 * @since 2026/11/04 23:24
 */


/**
 * 目标类
 */

public class UserServiceImpl implements UserService {
    @Override
    public void findAll() {
        System.out.println("查询用户的方法实现了");
    }

    @Override
    public void addUser() {
        System.out.println("新增用户的方法实现了");
    }

    @Override
    public void updateUser() {
        System.out.println("更新用户的方法实现了");
    }

    @Override
    public void delectUser() {
        System.out.println("删除用户的方法实现了");
    }
}
