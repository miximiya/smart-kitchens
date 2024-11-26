package Warn;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//删除警告信息
@WebServlet("/warnDelete")
public class warnDelete extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取要删除的用户ID
        String id = request.getParameter("id");

        // 创建UserServer对象
        warnServer warnServer = new warnServer();

        // 调用deleteData方法删除数据
       warnServer.warnDelete(id);

        // 重定向到UserPageServlet
        response.sendRedirect("loginRole");
    }
}
