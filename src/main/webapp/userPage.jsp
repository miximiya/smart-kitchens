<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户表</title>
    <style>
        body {
            display: flex;
            justify-content: space-between;
            background-color: #f3f3f3;
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
        }

        .left-column {
            width: 20%;
            padding: 20px;
            background-color: #444;
            color: #fff;
            border-radius: 5px;
            box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.1);
        }

        .left-column dl {
            margin-bottom: 20px;
        }

        .left-column dt {
            margin-top: 10px;
        }

        .left-column a {
            text-decoration: none;
            color: #fff;
            transition: color 0.3s ease;
        }

        .left-column dt:hover a {
            color: #00aaff;
        }

        .right-column {
            flex-grow: 1;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.1);
        }

        .table.user-table {
            text-align: center;
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        .table.user-table th,
        .table.user-table td {
            padding: 10px;
            border: 1px solid #ccc;
        }

        .table.user-table th {
            background-color: #f5f5f5;
            text-align: center;
            font-weight: bold;
            color: #333;
        }

        .table.user-table a {
            text-decoration: none;
            color: #333;
        }

        .table.user-table a:hover {
            color: #3366cc;
        }

        h1 {
            font-size: 24px;
            margin-bottom: 10px;
            color: #333;
            display: flex;
            justify-content: space-between;
            align-items: center;
            text-align: center;
            margin: 0% auto;
        }

        .button-container {
            display: flex;
            align-items: center;
        }

        .button-container a {
            display: inline-block;
            margin-right: 10px;
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        .button-container a:hover {
            background-color: #0056b3;
        }

        .page {
            text-align: center;
            margin-top: 20px;
        }

        .page a {
            text-decoration: none;
            padding: 10px;
            background-color: #ebebeb;
            color: #000;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        .page a:hover {
            background-color: #3363d2;
            color: #ffffff;
        }
    </style>
</head>
<body>
<div class="left-column">
    <dl>
        <dt><a href="login.jsp">退出登录</a></dt>
        <dt><a href="userPage" class="btn">用户信息</a></dt>
        <dt><a href="equipmentAdminPage" class="btn">设备信息</a></dt>
        <dt><a href="warnAdminPage" class="btn">警告信息</a></dt>
        <dt><a href="sensorAdminPage" class="btn">传感器信息</a></dt>
    </dl>
</div>

<div class="right-column">
    <h1>
        用户信息
        <p class="welcome-msg">欢迎管理员：<%= session.getAttribute("username") %>！</p>
        <div class="button-container">
            <a href="userIntRandom.jsp" class="btn">生成用户信息</a>
            <a href="userInt.jsp" class="btn">添加用户信息</a>
        </div>
    </h1>
    <table class="table user-table">
        <thead>
        <tr>
            <th>ID</th>
            <th>姓名</th>
            <th>电话号码</th>
            <th>地址</th>
            <th>角色</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="u" items="${user}">
            <tr>
                <td>${u.id}</td>
                <td>${u.name}</td>
                <td>${u.phone}</td>
                <td>${u.address}</td>
                <td>${u.role}</td>
                <td><a href="userDelet?id=${u.id}" class="delete-link">删除数据</a></td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="page">${page}</div>
</div>
</body>
</html>
<%--Expression Language--%>