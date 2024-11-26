package Sensor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//删除传感器信息
@WebServlet("/sensorDelete")
public class sensorDelete extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取要删除的设备ID
        String id = request.getParameter("id");

        // 创建DeviceServer对象
        sensorServer sensorServer = new sensorServer();

        // 调用deleteData方法删除数据
        sensorServer.sensorDelete(id);

        // 重定向到DevicePageServlet
        response.sendRedirect("loginRole");
    }
}
