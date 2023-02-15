package forum.controller;

import com.alibaba.fastjson.JSON;
import forum.pojo.FormBean;
import forum.pojo.User;
import forum.service.PublishService;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletContext;
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
//        request.setCharacterEncoding("UTF-8");
//        String topic = request.getParameter("topic");
//        String content = request.getParameter("content");
//        Part p = request.getPart("image");//正常
//        InputStream in = p.getInputStream();
//        OutputStream out = new FileOutputStream("d:/img/" +  p.getSubmittedFileName());
//        System.out.println(in);
//        IOUtils.copy(in, out);


        Collection<Part> parts = request.getParts();
        for (Part p : parts) {
//            System.out.println(p.getName());
            if (!p.getName().equals("image")) {
                continue;
            }
            String fileName = p.getSubmittedFileName();
//            System.out.println("fileName:" + fileName);
            if (fileName == null || fileName.isEmpty()) {
                continue;
            }
            InputStream in = p.getInputStream();
//            System.out.println("in:" + in);
            int r = (int) (Math.random() * 1000);
            OutputStream out = new FileOutputStream("d:/img/" + r + "_" + fileName);
            IOUtils.copy(in, out);
            out.flush();
            out.close();
            in.close();
        }
    }
}





