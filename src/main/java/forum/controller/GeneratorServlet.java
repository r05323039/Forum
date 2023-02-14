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

@WebServlet("/generator/*")
public class GeneratorServlet extends BaseServlet {
    private CategoryService categoryService = new CategoryService();
    private PostService postService = new PostService();
    private MsgService msgService = new MsgService();

    @Override
    public void init() throws ServletException {
        super.init();
        postService.PostGenerator();
        postService.likeGenerator();
        msgService.MsgGenerator();
    }

    public void HelloWorld(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        response.sendRedirect("http://localhost:8080/elitebaby/forum/home");
    }
    public void post(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        postService.PostGenerator();
        response.sendRedirect("http://localhost:8080/elitebaby/forum/home");
    }
    public void like(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        postService.likeGenerator();
        response.sendRedirect("http://localhost:8080/elitebaby/forum/home");
    }

    public void likeclean(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        postService.likeClean();
        response.sendRedirect("http://localhost:8080/elitebaby/forum/home");
    }





}
