package com.fufu.JDBC.demo1;

import java.sql.*;

public class JDBCTest {
    static void main() {

        //http://baidu.com
        //端口后面是数据库的名称
//        String url = "jdbc:mysql://124.220.74.123:3306/lesson:serverTimezone=Asia/Shanghai";
        String url = "jdbc:mysql://127.0.0.1:3307/lesson"
                + "?useSSL=false"
                + "&allowPublicKeyRetrieval=true"
                + "&serverTimezone=Asia/Shanghai"
                + "&characterEncoding=utf8";

        String userName = "root";

        String passWord = "root";

//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection connection =  DriverManager.getConnection(url , userName , passWord);
//
//            Statement statement = connection.createStatement();
//
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

        // JDBC 4.0 及以上版本无需显式 Class.forName，DriverManager 会自动通过 SPI 发现驱动
        try (Connection connection = DriverManager.getConnection(url, userName, passWord);
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("SELECT 1")) {

            if (rs.next()) {
                System.out.println("数据库连接并查询成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
