package Login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loginJudgment")
public class loginJudgment extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求参数
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = loginServlet.getUserRote(username); // 获取用户角色
        String inputCaptcha = request.getParameter("captcha");

        request.getSession().setAttribute("username", username);
        request.getSession().setAttribute("password", password);
        request.getSession().setAttribute("role", role);

        // 获取Session中保存的验证码
        String captcha = (String) request.getSession().getAttribute("captcha");


        // 验证验证码
        if (inputCaptcha != null && inputCaptcha.equalsIgnoreCase(captcha)) {
            response.sendRedirect("loginRole");
        } else {
            response.sendRedirect("loginFailed.jsp");
        }
    }
}
