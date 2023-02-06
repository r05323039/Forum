package forum.controller;

import forum.pojo.Category;
import forum.pojo.Post;
import forum.service.CategoryService;
import forum.service.PostService;
import login.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.util.ArrayList;

@WebServlet("/forum/*")
public class ForumServlet extends BaseServlet {
    private CategoryService C_service = new CategoryService();
    private PostService P_service = new PostService();



    public void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //取得參數
        boolean order = Boolean.parseBoolean(request.getParameter("order"));//null=false
        String swi = request.getParameter("switch");
        if (swi != null) {
            order = !order;
        }
        System.out.println("order:" + order);

        String category_id = request.getParameter("categoryid");
        String topic = request.getParameter("topic");
        System.out.println(category_id + "," + topic);
        //呼叫+封裝
        ArrayList<Post> posts = P_service.getAll(order, category_id, topic);
        request.setAttribute("posts", posts);

        ArrayList<ArrayList<Category>> Cs = C_service.getAll();
        request.setAttribute("LCs", Cs.get(0));
        request.setAttribute("HCs", Cs.get(1));

        request.setAttribute("order", order);
        //傳送
        request.getRequestDispatcher("/forum.jsp").forward(request, response);
    }

    public void like(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        P_service.like_generator();
        response.sendRedirect("http://localhost:8080/elitebaby/forum/home");
    }

    public void likeclean(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        P_service.like_clean();
        response.sendRedirect("http://localhost:8080/elitebaby/forum/home");
    }

    public void likeclick(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int user_id = user.getUserId();
        int post_id = Integer.parseInt(request.getParameter("postid"));
        P_service.like_click(post_id, user_id);
        response.sendRedirect("http://localhost:8080/elitebaby/forum/home");
    }
}
