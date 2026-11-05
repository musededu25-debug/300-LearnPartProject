package com.fufu.jdbc.demo1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Test02 {

    public void connectMysql() throws Exception {
        //Class.forName("com.mysql.cj.jdbc.Driver");

        //String url="jdbc:mysql://127.0.0.1:3307/exercise2";
        String url = "jdbc:mysql://127.0.0.1:3307/exercise2"
                + "?connectionTimeZone=Asia/Shanghai"
                + "&sslMode=DISABLED"
                + "&allowPublicKeyRetrieval=true";
        String username = "root";
        String password = "root";
        Connection connection = DriverManager.getConnection(url, username, password);

        Statement statement = connection.createStatement();

        String sql1 = "update stu set age = 20 where id = 1;";
        String sql2 = "update stu set age = 100 where id = 2;";


        try {
            //开启事务
            connection.setAutoCommit(false);

            int i = statement.executeUpdate(sql1);
            int i2 = statement.executeUpdate(sql2);


            System.out.println(i + "受影响行数");
            System.out.println(i2 + "受影响行数");

        } catch (Exception e) {
            //回滚事务
            connection.rollback();
            throw new RuntimeException(e);
        }


        //提交事务
        connection.commit();
        statement.close();
        connection.close();

    }
}
