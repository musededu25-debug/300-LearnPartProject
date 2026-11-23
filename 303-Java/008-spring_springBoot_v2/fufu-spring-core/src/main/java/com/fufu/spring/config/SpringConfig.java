package com.fufu.spring.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.fufu.spring")
@PropertySource("classpath:jdbc.properties")
//告诉Spring要靠注解开发的aop
@EnableAspectJAutoProxy(proxyTargetClass = true)
//导入配置类
@Import({JdbcConfig.class,MybatisConfig.class})
public class SpringConfig {

}
