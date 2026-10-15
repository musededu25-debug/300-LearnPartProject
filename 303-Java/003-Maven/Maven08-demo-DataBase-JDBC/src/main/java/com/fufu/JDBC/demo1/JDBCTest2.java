package com.fufu.JDBC.demo1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCTest2 {

    static void main() {

        String url = "jdbc:mysql://127.0.0.1:3307/lesson"
                + "?connectionTimeZone=Asia/Shanghai"
                + "&sslMode=DISABLED"
                + "&allowPublicKeyRetrieval=true";
        String userName = "root";
        String passWord = "root";
        List<Account2> list =new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url , userName , passWord);

            Statement s = connection.createStatement();
            String updateSql = "UPDATE account SET balance = balance + 10000 WHERE account=123457";
            int number = s.executeUpdate(updateSql);

            System.out.println(updateSql);
            System.out.println("影响的行数为：" + number);

            String sql = "select account,balance,state from account";
            ResultSet rs = s.executeQuery(sql);

            while(rs.next()){
                String account = rs.getString("account");
                double balance = rs.getDouble("balance");
                int state = rs.getInt("state");
                //System.out.println(account + "," + balance +"," + state);
                Account2 account2 = new Account2(account,balance,state);
                list.add(account2);
            }

            rs.close();
            s.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {

        }
        list.forEach(System.out::println);

    }

}
