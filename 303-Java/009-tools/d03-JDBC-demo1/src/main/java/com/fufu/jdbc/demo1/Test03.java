package com.fufu.jdbc.demo1;

import com.fufu.jdbc.demo1.pojo.Account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Test03 {

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

        ResultSet resultSet = statement.executeQuery("select * from user");

        ArrayList<Account> accountArrayList = new ArrayList<>();

        while (resultSet.next()) {
            Account account = new Account();
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            double balance = resultSet.getDouble("balance");
            account.setId(id);
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
