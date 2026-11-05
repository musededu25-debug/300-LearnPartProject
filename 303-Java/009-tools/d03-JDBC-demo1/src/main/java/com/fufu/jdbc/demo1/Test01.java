package com.fufu.jdbc.demo1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Test01 {

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

        String sql = "update stu set age = 20 where id = 1;";

        int i = statement.executeUpdate(sql);

        System.out.println(i + "受影响行数");

        statement.close();
        connection.close();

    }
}
