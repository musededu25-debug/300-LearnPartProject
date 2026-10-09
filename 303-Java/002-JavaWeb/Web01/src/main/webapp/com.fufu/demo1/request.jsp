<%--
  Created by IntelliJ IDEA.
  User: m
  Date: 2026/9/12
  Time: 12:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Request训练</title>
</head>
<body>
  <h1>Request是Web服务器内置对象</h1>

  <form action="info.jsp" method="post" name="myform">
    <label>用户名:</label>
    <input type="text" name="username" placeholder="请输入你的名字" checked>
    <br><br>

    <label>密码：</label>
    <input type="password" name="password" placeholder="请输入密码" checked>
    <br><br>

    性别：
    <input type="radio" name="usergender" value="male" checked >
    <label>男</label>
    <input type ="radio" name="usergender" value="female">
    <label>女</label>
    <br><br>


    <span>城市:</span>
    <select name="city">
      <option value="zz" >郑州</option>
      <option value="wh">武汉</option>
      <option value="bj" selected>北京</option>
    </select>
    <br><br>

    <span>爱好：</span>
    <input type="checkbox" name="userhobby" value="music">
    <label>音乐</label>
    <input type="checkbox" name="userhobby" value="mv">
    <label>mv</label>
    <input type="checkbox" name="userhobby" value="av">
    <label>av</label>
    <input type="checkbox" name="userhobby" value="game">
    <label>game</label>
    <br><br>

    <button type="submit" >提交</button>
  </form>

</body>
</html>
