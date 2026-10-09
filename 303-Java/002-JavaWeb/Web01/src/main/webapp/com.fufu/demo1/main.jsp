<%--
  Created by IntelliJ IDEA.
  User: m
  Date: 2026/9/12
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <%
    String name =request.getParameter("username");
    String password=request.getParameter("password");

  %>

姓名：<%=name%>
<br>
密码：<%=password%>

</body>
</html>
