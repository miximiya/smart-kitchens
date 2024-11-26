package Login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loginRole")
public class loginRole extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从Session中获取角色信息
        String username = (String) request.getSession().getAttribute("username");
        String password = (String) request.getSession().getAttribute("password");

        // 验证用户身份
        boolean isValidUser = loginServlet.validateUser(username, password);
        if (isValidUser) {
            String role = loginServlet.getUserRote(username); // 获取用户角色
            // 将角色信息存储到Session中
            request.getSession().setAttribute("role", role);
            // 根据用户角色进行页面重定向
            if ("管理员".equals(role)) {
                response.sendRedirect("userPage");
            } else if ("用户".equals(role)) {
                response.sendRedirect("equipmentUserPage");
            } else {
                response.sendRedirect("loginFailed.jsp");
            }
        }
    }
}


