<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="User.userServer" %>
<!DOCTYPE html>
<html>
<head>
    <title>随机添加用户信息</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 20px;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 90vh;
        }

        .container {
            text-align: center;
        }

        h2 {
            color: #333333;
            margin-top: 30px;
        }

        .success-message {
            margin-top: 20px;
            padding: 10px;
            background-color: #dff0d8;
            border: 1px solid #d6e9c6;
            color: #3c763d;
        }

        .return-link {
            display: block;
            margin-top: 20px;
            text-decoration: none;
            color: #337ab7;
        }
    </style>
</head>
<body>
<div class="container">
    <%
        userServer userServer = new userServer();
        userServer.userIntRandom();
    %>

    <h2>用户生成成功</h2>
    <div class="success-message">已生成10个随机用户。</div>
    <a href="userPage" class="return-link">返回主页</a>
</div>
</body>
</html>
