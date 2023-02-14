package forum.controller;

import com.alibaba.fastjson.JSON;
import forum.pojo.Category;
import forum.pojo.Post;
import forum.pojo.PostBean;
import forum.pojo.User;
import forum.service.CategoryService;
import forum.service.MsgService;
import forum.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/msg/*")
public class MsgServlet extends BaseServlet {
    private MsgService msgService = new MsgService();


    public void like(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        msgService.likeGenerator();
        response.sendRedirect("http://localhost:8080/elitebaby/forum/home");
    }

    public void likeclean(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        msgService.likeClean();
        response.sendRedirect("http://localhost:8080/elitebaby/forum/home");
    }

    public void likeclick(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();
        int msgId = Integer.parseInt(request.getParameter("msgId"));
        int count = msgService.likeClick(msgId, userId);
        responseJOSN(response, String.valueOf(count));
    }
}
