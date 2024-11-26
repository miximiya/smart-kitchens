package User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//删除用户信息
@WebServlet("/userDelet")
public class userDelet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取要删除的用户ID
        String id = request.getParameter("id");

        // 创建UserServer对象
        userServer userServer = new userServer();
        userServer.deleteData(id);

        response.sendRedirect("loginRole");
    }
}
