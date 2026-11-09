package com.fufu.jdbc.demo1.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.util.Properties;

public class DruidDemo {

    public void link() throws Exception {

        Properties prop = new Properties();
        prop.load(new FileInputStream("src/resources/druid.properties"));

        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
    }
}
