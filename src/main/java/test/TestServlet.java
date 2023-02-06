package test;

import forum.pojo.Category;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(name = "TestServlet", value = "/TestServlet")
public class TestServlet extends HttpServlet {
    Dao dao = new Dao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //取得參數
        String category = null;
        String categoryURL = request.getQueryString();
        System.out.println("categoryURL:" + categoryURL);

        String paraCate = request.getParameter("category");
        System.out.println(paraCate);
//        if (categoryURL != null) {
//            category = URLDecoder.decode(categoryURL, "UTF-8");
//            System.out.println("category:" + category);
//        } else {
//            System.out.println("category is null");
//        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
