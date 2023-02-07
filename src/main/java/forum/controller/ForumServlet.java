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
    private CategoryService categoryService = new CategoryService();
    private PostService postService = new PostService();



    public void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //取得參數
        boolean order = Boolean.parseBoolean(request.getParameter("order"));//null=false
        String swi = request.getParameter("switch");
        if (swi != null) {
            order = !order;
        }
        System.out.println("order:" + order);

        String categoryId = request.getParameter("categoryId");
        String topic = request.getParameter("topic");
        System.out.println(categoryId + "," + topic);
        //呼叫+封裝
        ArrayList<Post> posts = postService.getAll(order, categoryId, topic);
        request.setAttribute("posts", posts);

        ArrayList<ArrayList<Category>> Cs = categoryService.getAll();
        request.setAttribute("LCs", Cs.get(0));
        request.setAttribute("HCs", Cs.get(1));

        request.setAttribute("order", order);
        //傳送
        request.getRequestDispatcher("/forum.jsp").forward(request, response);
    }

    public void like(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        postService.likeGenerator();
        response.sendRedirect("http://localhost:8080/elitebaby/forum/home");
    }

    public void likeclean(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        postService.likeClean();
        response.sendRedirect("http://localhost:8080/elitebaby/forum/home");
    }

    public void likeclick(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();
        int postId = Integer.parseInt(request.getParameter("postId"));
        postService.likeClick(postId,userId);
        response.sendRedirect("http://localhost:8080/elitebaby/forum/home");
    }
}
