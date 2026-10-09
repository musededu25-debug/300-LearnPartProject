<%@ page import="java.util.Arrays" %><%--
  Created by IntelliJ IDEA.
  User: m
  Date: 2026/9/12
  Time: 12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>接收request返回的内容</title>
</head>
<body>
  <%
    request.setCharacterEncoding("UTF-8");
    String name=request.getParameter("username");
    String password= request.getParameter("password");
    String city=request.getParameter("city");

    String[] hobby=request.getParameterValues("userhobby");

  %>

  <div>用户名：<%=name%></div>
  <div>密码：<%=password%></div>
  <div>城市:<%=city%></div>
  <div>爱好：<%=Arrays.toString(hobby)%></div>

</body>
</html>
