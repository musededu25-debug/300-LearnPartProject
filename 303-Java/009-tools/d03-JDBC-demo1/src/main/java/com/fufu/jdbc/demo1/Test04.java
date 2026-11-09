package com.fufu.jdbc.demo1;

import com.fufu.jdbc.demo1.pojo.Account;

import java.sql.*;
import java.util.ArrayList;

/**
 * PrepareStatement
 */

public class Test04 {

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

        int id = 1;

        //Statement statement = connection.createStatement();

        String sql = "select * from stu where id = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        ArrayList<Account> accountArrayList = new ArrayList<>();

        while (resultSet.next()) {
            Account account = new Account();
            int id0 = resultSet.getInt("id");
            String name = resultSet.getString("name");
            double balance = resultSet.getDouble("balance");
            account.setId(id0);
            account.setName(name);
            account.setBalance(balance);

            accountArrayList.add(account);
        }
        accountArrayList.forEach(System.out::println);

        resultSet.close();
        statement.close();
        connection.close();
    }
}
