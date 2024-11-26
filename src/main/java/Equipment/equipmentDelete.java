package Equipment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//删除设备信息
@WebServlet("/equipmentDelete")
public class equipmentDelete extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取要删除的设备ID
        String id = request.getParameter("id");

        // 创建DeviceServer对象
        equipmentServer equipmentserver = new equipmentServer();
        equipmentserver.equipmentDelete(id);
        response.sendRedirect("loginRole");

    }
}
