<%--
  Created by IntelliJ IDEA.
  User: m
  Date: 2026/9/12
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Response练习</title>
</head>
<body>

    <form action="login.jsp" method="post">
        <label>姓名：</label>
        <input type="text" name="username"  placeholder="请输入你的名字" checked>
        <br><br>

        <label>密码：</label>
        <input type="password" name="password" placeholder="请输入密码" checked >

        <button type="submit">提交</button>
    </form>

 </body>
</html>
