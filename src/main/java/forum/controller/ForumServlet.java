package forum.controller;

import com.alibaba.fastjson.JSON;
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
        request.setCharacterEncoding("UTF-8");
        boolean order = Boolean.parseBoolean(request.getParameter("order"));//null=false
        String swi = request.getParameter("switch");
        if (swi != null) {
            order = !order;
        }
        String categoryId = request.getParameter("categoryId");
        String topic = request.getParameter("topic");
        System.out.println("order:" + order + ", categoryId:" + categoryId + ", topic:" + topic);
        //呼叫+封裝
        ArrayList<Post> posts = postService.getAll(order, categoryId, topic);
        request.setAttribute("posts", posts);

        ArrayList<ArrayList<Category>> Cs = categoryService.getAll();
        request.setAttribute("LCs", Cs.get(0));
        request.setAttribute("HCs", Cs.get(1));

        request.setAttribute("categoryId", categoryId);
        request.setAttribute("order", order);
        request.setAttribute("topic", topic);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            int userId = user.getUserId();
            ArrayList<Category> collectedCategories = categoryService.getCollectedCategories(userId);
            request.setAttribute("CCs", collectedCategories);
        }

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

    public void likeclick(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();
        int postId = Integer.parseInt(request.getParameter("postId"));
        int count = postService.likeClick(postId, userId);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(String.valueOf(count));
    }

    public void collectedCategories(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();
        ArrayList<Category> collectedCategories = categoryService.getCollectedCategories(userId);
        String J_categories = JSON.toJSONString(collectedCategories);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(String.valueOf(J_categories));
        //OK
    }

    public void categoryCollect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        boolean exist = categoryService.collect(userId, categoryId);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(String.valueOf(exist));
    }
}
