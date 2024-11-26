package Sensor;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//管理员添加传感器信息
@WebServlet("/sensorInt")
public class sensorInt extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 设置请求和响应的字符编码为UTF-8
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        // 创建DeviceServer对象
        sensorServer sensorServer = new sensorServer();

        // 创建Device对象，并设置设备信息
        Sensor sensor = new Sensor();
        sensor.setName(request.getParameter("name"));
        sensor.setData(request.getParameter("data"));
        sensor.setTime(request.getParameter("time"));


        sensorServer.sensorIntAutonomic(sensor);
        response.sendRedirect("loginRole");
    }
}
