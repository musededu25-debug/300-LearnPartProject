package com.fufu.spring.p01;

import com.fufu.spring.p01.config.P01Config;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * p01 模块环境自检：只验证容器能启动，不是练习题的答案。
 */
@SpringJUnitConfig(P01Config.class)
class ModuleSmokeTest {

    @Autowired
    private ApplicationContext context;

    @Test
    @DisplayName("环境自检：p01 容器能启动并注入 ApplicationContext")
    void contextLoads() {
        assertNotNull(context, "ApplicationContext 应该被注入");
        assertTrue(context.containsBean("p01Config"), "P01Config 应该被注册为 Bean");
        System.out.println("p01 环境 OK，当前 Bean 数量：" + context.getBeanDefinitionCount());
    }
}
