<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>自主添加警告信息</title>
    <style>
        /* 页面基础样式 */
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f7f7f7;  /* 页面背景色改为浅灰色 */
            color: #333;  /* 默认文字颜色为深灰色 */
            margin: 0;
            padding: 0;
        }

        .container {
            width: 40%;
            margin: 50px auto;
            padding: 30px;
            background-color: #fff;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            border-radius: 12px;
            position: relative;
            font-size: 16px;
        }

        /* 标题样式 */
        .title {
            font-size: 28px;
            font-weight: bold;
            text-align: center;
            color: #333;
            margin-bottom: 30px;
            border-bottom: 3px solid #333;  /* 标题下方添加黑色下划线 */
            padding-bottom: 15px;
        }

        /* 表格样式 */
        table {
            width: 100%;
            border-collapse: separate;
            border-spacing: 0 15px;  /* 增加行之间的垂直间距 */
        }

        td {
            padding: 5px;
            padding-left:20px;
            font-size: 18px;
            color: #333;
        }

        /* 输入框样式 */
        input[type="text"], input[type="password"], input[type="datetime-local"] {
            width: 100%;
            height: 40px; /* 增加输入框的高度，便于输入 */
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 8px;
            box-sizing: border-box;
            margin-bottom: 20px;  /* 增加输入框之间的间距 */
        }

        /* 提交和重置按钮样式 */
        input[type="submit"], input[type="reset"], .back-button {
            padding: 12px 80px;
            background-color: #333;  /* 按钮背景颜色为黑色 */
            color: white;
            border: none;
            text-align: center;
            font-size: 20px;
            cursor: pointer;
            border-radius: 10px;
            transition: background-color 0.3s ease;
        }

        /* 返回按钮 */
        .back-button {
            position: absolute;
            top: 15px;
            left: 15px;
            padding: 12px 25px;
            background-color: #333;
            color: white;
            border: none;
            font-size: 18px;
            cursor: pointer;
            border-radius: 8px;
            text-align: center;
        }

        /* 按钮悬停效果 */
        .back-button:hover,
        input[type="submit"]:hover,
        input[type="reset"]:hover {
            background-color: #555;  /* 按钮悬停时变为深灰色 */
        }

        /* 下划线样式 */
        .underline {
            text-align: center;
            margin-top: 30px;
        }
    </style>
    <script>
        // JavaScript：设置警告时间默认为当前时间
        window.onload = function() {
            // 获取当前时间并格式化为"yyyy-MM-ddThh:mm"的格式
            const currentDate = new Date();
            const formattedDate = currentDate.toISOString().slice(0, 16);  // 取前16个字符：YYYY-MM-DDTHH:MM
            // 设置默认时间到警告时间的输入框
            document.getElementById("warnTime").value = formattedDate;
        };

        // JavaScript：提交表单后弹出提示框并刷新页面
        function handleSubmit() {
            // 提交表单数据
            document.forms['form1'].submit();
            // 弹出提示框
            alert("添加成功，请返回");
        }
    </script>
</head>
<body>
<div class="container">
    <!-- 返回按钮，放置在container的左上角 -->
    <button class="back-button" onclick="window.location.href='loginRole';">返回</button>

    <form name="form1" method="post" action="warnInt" onsubmit="handleSubmit()">
        <div class="title">请添加警告信息</div>
        <table>
            <tr>
                <td>警告类型:</td>
                <td><input type="text" name="source" value="" class="txtBox" required /></td>
            </tr>
            <tr>
                <td>警告消息:</td>
                <td><input type="text" name="message" value="" class="txtBox" required /></td>
            </tr>
            <tr>
                <td>警告时间:</td>
                <td><input type="datetime-local" name="time" id="warnTime" class="txtBox" required /></td> <!-- 使用日期和时间选择器 -->
            </tr>
            <tr>
                <td colspan="2" class="underline">
                    <input type="submit" value="添加"/>
                    <input type="reset" value="取消"/>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
