package com.fufu;

import com.fufu.Service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestAccount {

    @Test
    public void test01() {
        /*
         * 用 try-with-resources 包住容器：Spring 容器属于重量级资源，
         * 用完必须 close()，否则 JVM 退出前不会销毁单例、也不会触发销毁回调。
         */
        try (ClassPathXmlApplicationContext context =
                     new ClassPathXmlApplicationContext("applicationContext.xml")) {

            // 按接口类型取 Bean，连强转都省了 —— 这正是 IoC 面向接口编程的价值
            AccountService accountService =
                    context.getBean("accountService", AccountService.class);
            assertNotNull(accountService, "Spring 容器应当能装配出 accountService");

            /*
             * 【改进】原来是直接调用 add()，无论 Service 有没有真正调到 DAO，
             * 测试都是绿的（只要不抛异常就算过）—— 这种"假通过"验证不了任何东西。
             *
             * 这里把 System.out 临时替换成内存缓冲区，截获调用链产生的输出，
             * 再断言里面确实出现了 DAO 层的那行日志。
             * 这样一旦 @Autowired 注入失败或 add() 忘记委托，测试会立刻失败。
             */
            PrintStream originalOut = System.out;
            ByteArrayOutputStream captured = new ByteArrayOutputStream();
            String output;
            try {
                System.setOut(new PrintStream(captured, true, StandardCharsets.UTF_8));
                accountService.add();
            } finally {
                // 必须还原，否则后续测试和控制台输出都会被吞掉
                System.setOut(originalOut);
            }
            output = captured.toString(StandardCharsets.UTF_8);

            System.out.println("[测试捕获到的输出] " + output.trim());
            assertTrue(output.contains("AccountDaoImpl - add success"),
                    "Service 应当把调用委托给 DAO，实际输出为：" + output);
        }
    }
}
