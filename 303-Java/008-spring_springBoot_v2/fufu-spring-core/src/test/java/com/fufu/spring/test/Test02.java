package com.fufu.spring.test;

import com.fufu.spring.config.SpringConfig;
import com.fufu.spring.mapper.UserMapper;
import com.fufu.spring.pojo.User;
import com.fufu.spring.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Spring 7 整合 JUnit 6 + HikariCP + MyBatis 端到端验证
 */
@SpringJUnitConfig(SpringConfig.class)
public class Test02 {

    @Autowired
    private BookService bookService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserMapper userMapper;

    @Test
    @DisplayName("1. 验证 Spring 容器与 HikariCP 能否真正连上 MySQL")
    void testDataSourceConnection() throws SQLException {
        Assertions.assertNotNull(bookService, "BookService 应该被容器成功注入");
        Assertions.assertNotNull(dataSource, "DataSource 应该被容器成功注入");

        try (Connection conn = dataSource.getConnection()) {
            Assertions.assertTrue(conn.isValid(2), "数据库连接应有效");
            System.out.println("连接成功 -> 数据库: " + conn.getCatalog()
                    + " | 产品: " + conn.getMetaData().getDatabaseProductName()
                    + " " + conn.getMetaData().getDatabaseProductVersion());
        }
    }

    @Test
    @DisplayName("2. 验证 Spring 整合 MyBatis 查询 tb_user 表")
    void testMyBatisSelectAll() {
        Assertions.assertNotNull(userMapper, "UserMapper 代理对象应该被容器成功注入");

        List<User> users = userMapper.selectAll();
        Assertions.assertFalse(users.isEmpty(), "tb_user 表应该能查出数据");

        System.out.println("查出 tb_user 共 " + users.size() + " 条：");
        users.forEach(System.out::println);
    }
}
