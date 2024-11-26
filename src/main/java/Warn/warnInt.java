package Warn;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//管理员添加警告信息
@WebServlet("/warnInt")
public class warnInt extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 设置请求和响应的字符编码为UTF-8
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        // 创建DeviceServer对象
        warnServer warnServer = new warnServer();

        // 创建Device对象，并设置设备信息
        Warn warn = new Warn();
        warn.setSource(request.getParameter("source"));
        warn.setMessages(request.getParameter("message"));
        warn.setTime(request.getParameter("time"));
        warnServer.warnIntAutonomic(warn);
        response.sendRedirect("loginRole");

    }
}
