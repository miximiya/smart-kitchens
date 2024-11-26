<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>登录界面</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            padding-top: 50px;
        }

        h1 {
            text-align: center;
        }

        form {
            max-width: 400px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            margin-bottom: 10px;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #000;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #3363d2;
        }
    </style>
</head>
<body>
<h1>登录</h1>
<form action="loginJudgment" method="POST">
    <label for="username">用户名:</label>
    <input type="text" id="username" name="username"><br>

    <label for="password">密码:</label>
    <input type="password" id="password" name="password"><br>

    <label for="captcha">验证码:</label>
    <input type="text" id="captcha" name="captcha">
    <a href="javascript:void(0);" onclick="refreshCaptcha();">
        <img id="captchaImg" src="captcha" alt="Captcha">
    </a>

    <input type="submit" value="登录">
</form>

<script>
    function refreshCaptcha() {
        var captchaImg = document.getElementById("captchaImg");
        captchaImg.src = "captcha?action=refresh&timestamp=" + new Date().getTime();
    }
</script>
</body>
</html>
<%--doGet方法和doPost方法在Java Servlet中的主要区别如下：--%>

<%--请求类型：doGet方法用于处理HTTP GET请求，而doPost方法用于处理HTTP POST请求。GET请求将数据附加在URL的查询参数中，而POST请求则将数据包含在请求正文中。--%>

<%--数据传递：GET请求通过URL的查询参数传递数据，这些参数可以从URL中提取并在doGet方法中进行处理。而POST请求将数据包含在请求的正文中，可以通过HttpServletRequest对象的方法（如getParameter()）来获取这些数据。--%>

<%--安全性：由于GET请求将数据暴露在URL中，因此可能不适合传输敏感信息（如密码）。相比之下，POST请求将数据作为请求正文发送，因此更适合传输敏感信息。--%>

<%--缓存：GET请求可以被浏览器缓存，因为它们是幂等的（即多次请求返回相同的结果）。POST请求通常不会被缓存，因为它们可能对服务器状态产生影响。--%>

<%--安全性提示：由于GET请求将数据显示在URL中，所以当表单提交敏感数据时（如密码），浏览器可能会发出警告。而POST请求不会显示数据在URL中，因此没有这个安全性问题。--%>

<%--需要根据实际情况选择使用doGet或doPost方法。通常，如果您需要从URL中获取数据，或者处理不会对服务器状态产生影响的请求，可以使用doGet方法。如果您需要传输敏感信息或执行可能对服务器状态有副作用的操作，则应使用doPost方法。--%>