package Equipment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


//设备表管理员分页
@WebServlet("/equipmentAdminPage")
public class equipmentAdminPage extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 设置响应内容类型为HTML，字符集为UTF-8
        response.setContentType("text/html; charset=UTF-8");

        final int SIZE = 10;
        Integer page = null;
        try {
            // 获取请求参数中的页码
            page = Integer.parseInt(request.getParameter("page"));
        } catch (Exception e) {
            // 若页码解析失败，则默认为第一页
            page = 1;
        }

        // 计算查询起始位置
        int start = (page - 1) * SIZE;

        // 创建DeviceServer对象
        equipmentServer equipmentserver = new equipmentServer();

        // 查询指定页码的设备列表
        List<Equipment> list = equipmentserver.queryPage(start, SIZE);

        // 查询总记录数
        int total = equipmentserver.getTotalCount();

        // 计算总页数
        int pageNum = total / SIZE;
        if (total % SIZE != 0) {
            pageNum += 1;
        }

        // 构建分页链接字符串
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= pageNum; i++) {
            String str = "<a href=\"#url\">" + "&nbsp;&nbsp;&nbsp;" + i + "&nbsp;&nbsp;&nbsp;" + "</a>&nbsp;&nbsp;";
            str = str.replace("#url", String.format("/smart_kitchens_war/equipmentAdminPage?page=%d", i));
            if (page == i) {
                str = "&nbsp;&nbsp;&nbsp;" + i + "&nbsp;&nbsp;&nbsp;";
            }
            sb.append(str);
        }

        // 将分页链接字符串和设备列表设置为请求属性
        request.setAttribute("page", sb.toString());
        request.setAttribute("equipment", list);

        request.getRequestDispatcher("equipmentAdminPage.jsp").forward(request, response);
    }
}