package User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
//用户表分页
@WebServlet("/userPage")
public class userPage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");

        final int SIZE = 10;
        Integer page = null;
        try {

            page = Integer.parseInt(request.getParameter("page"));
        } catch (Exception e) {

            page = 1;
        }


        int start = (page - 1) * SIZE;


        userServer userServer = new userServer();

        List<User> list = userServer.queryPage(start, SIZE);


        int total = userServer.getTotalCount();

        int pageNum = total / SIZE;
        if (total % SIZE != 0) {
            pageNum += 1;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= pageNum; i++) {
            String str = "<a href=\"#url\">" +"&nbsp;&nbsp;&nbsp;" + i +"&nbsp;&nbsp;&nbsp;" + "</a>&nbsp;&nbsp;";
            str = str.replace("#url", String.format("/smart_kitchens_war/userPage?page=%d", i));
            if (page == i) {
                str = "&nbsp;&nbsp;&nbsp;" + i + "&nbsp;&nbsp;&nbsp;";
            }
            sb.append(str);
        }

        request.setAttribute("page", sb.toString());
        request.setAttribute("user", list);

        request.getRequestDispatcher("userPage.jsp").forward(request, response);
    }
}

