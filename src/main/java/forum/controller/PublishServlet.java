package forum.controller;

import com.alibaba.fastjson.JSON;
import forum.pojo.FormBean;
import forum.pojo.Post;
import forum.pojo.User;
import forum.service.PostService;
import forum.service.PublishService;

import javax.servlet.ServletException;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

@MultipartConfig
@WebServlet("/publish/*")
public class PublishServlet extends BaseServlet {
    private PublishService publishService = new PublishService();

    private PostService postService = new PostService();

    public void getForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();
        FormBean formBean = publishService.getForm(userId);
//        System.out.println(formBean);
        String J_formBean = JSON.toJSONString(formBean);
        responseJOSN(response, J_formBean);
    }

    public void submit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();
        String category = request.getParameter("category");
        String topic = request.getParameter("topic");
        String content = request.getParameter("content");
        Post post = new Post(userId, category, topic, content);//封裝Post參數

        Collection<Part> parts = request.getParts();
        ArrayList<InputStream> ins = new ArrayList<>();
        for (Part p : parts) {
            if (!p.getName().equals("image")) {
                continue;
            }
            String fileName = p.getSubmittedFileName();
            if (fileName == null || fileName.isEmpty()) {
                continue;
            }
            ins.add(p.getInputStream());
        }
        publishService.submit(post, ins);
        response.sendRedirect("http://localhost:8080/elitebaby/forum/home");
    }

    public void aaa(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        boolean order = Boolean.parseBoolean(request.getParameter("order"));//null=false
        String swi = request.getParameter("switch");
        if (swi != null) {
            order = !order;
        }
        String categoryId = request.getParameter("categoryId");
        String topic = request.getParameter("topic");
        ArrayList<Post> posts = postService.getAll(order, categoryId, topic);

        String s = JSON.toJSONString(posts);
        responseJOSN(response, s);
    }
}





