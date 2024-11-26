package User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//添加用户信息
@WebServlet("/userInt")
public class userInt extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 设置请求和响应的字符编码为UTF-8
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        // 创建UserServer对象
        userServer userServer = new userServer();

        // 创建User对象，并设置用户信息
        User user = new User();
        user.setName(request.getParameter("name"));
        user.setPassword(request.getParameter("password"));
        user.setPhone(request.getParameter("phone"));
        user.setAddress(request.getParameter("address"));
        user.setRole(request.getParameter("role"));

        // 调用addUser方法添加用户
        userServer.userIntAutonomic(user);

        response.sendRedirect("loginRole");

    }
}
