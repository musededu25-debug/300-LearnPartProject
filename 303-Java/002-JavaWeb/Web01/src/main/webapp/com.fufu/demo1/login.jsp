<%--
  Created by IntelliJ IDEA.
  User: m
  Date: 2026/9/12
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
  String name=request.getParameter("username");
  String password=request.getParameter("password");

  if(name.equals("zhangsan")&&password.equals("123456"))
    {
      RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
      dispatcher.forward(request,response);
    }

%>
