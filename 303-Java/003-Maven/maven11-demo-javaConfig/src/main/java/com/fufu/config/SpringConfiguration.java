package com.fufu.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.sql.DataSource; // 【修正1】必须导入 Java 官方的数据源接口
import java.beans.PropertyVetoException;

@Configuration
@ComponentScan(value = "com.fufu")
public class SpringConfiguration {

    @Bean(name = "dataSource")
    public DataSource createDataSource() { // 返回类型建议写父接口 DataSource
        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        try {
            // 【修正2】驱动类写错了，不能写 C3P0 的类名，必须是 MySQL 的驱动！
            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");

            // 【修正3】Java 字符串里绝对不能用 XML 的转义字符 &amp;，必须改回普通的 &
            dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3307/lesson?connectionTimeZone=Asia/Shanghai&sslMode=DISABLED&allowPublicKeyRetrieval=true");

            dataSource.setUser("root");
            dataSource.setPassword("root");
            return dataSource;
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean(name = "runner")
    @Scope("prototype") // 【修正4】QueryRunner 必须是多例的，否则高并发下线程不安全！等同于 XML 里的 scope="prototype"
    public QueryRunner createQueryRunner(@Qualifier("dataSource") DataSource dataSource) {

        // 【修正5】你原来 new 完直接返回了，根本没有把 dataSource 交给 QueryRunner！必须通过构造方法传进去。
        QueryRunner runner = new QueryRunner(dataSource);

        return runner;
    }
}