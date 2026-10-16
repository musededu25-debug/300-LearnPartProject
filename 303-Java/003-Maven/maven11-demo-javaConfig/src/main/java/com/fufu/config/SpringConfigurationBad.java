package com.fufu.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.beans.PropertyVetoException;

@Configuration
@ComponentScan(value = "com.fufu")
public class SpringConfigurationBad {

    @Bean(name = "dataSource")
    public ComboPooledDataSource dataSources (){
        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        try {
            dataSource.setDriverClass("com.mchange.v2.c3p0.ComboPooledDataSource");
            dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3307/lesson?connectionTimeZone=Asia/Shanghai&amp;sslMode=DISABLED&amp;allowPublicKeyRetrieval=true");
            dataSource.setUser("root");
            dataSource.setPassword("root");
            return dataSource;
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
    }


//    @Bean(name = "runner")
//    public QueryRunner runner(@Qualifier("dataSource") DataSources dataSources){
//
//        QueryRunner  runner =new QueryRunner();
//
//        return runner;
//    }


}
