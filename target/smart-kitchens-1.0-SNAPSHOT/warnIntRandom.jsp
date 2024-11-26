<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="Warn.warnServer" %>
<!DOCTYPE html>
<html>
<head>
    <title>随机添加警告信息</title>
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
        warnServer warnServer = new warnServer();
        warnServer.warnIntRandom(); // 调用插入数据的方法
    %>

    <h2>警告信息生成成功</h2>
    <div class="success-message">已成功生成10条警告信息。</div>
    <a href="warnAdminPage" class="return-link">返回主页</a>
</div>
</body>
</html>