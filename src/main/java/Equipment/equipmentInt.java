package Equipment;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//管理员添加设备信息
@WebServlet("/equipmentInt")
public class equipmentInt extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 设置请求和响应的字符编码为UTF-8
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        // 创建DeviceServer对象
        equipmentServer equipmentserver = new equipmentServer();

        // 创建Device对象，并设置设备信息
        Equipment equipment = new Equipment();
        equipment.setName(request.getParameter("name"));
        equipment.setManufacturer(request.getParameter("manufacturer"));
        equipment.setStatus(request.getParameter("status"));
        equipmentserver.equipmentIntAutonomic(equipment);

        response.sendRedirect("loginRole");

    }
}
