package forum.controller;

import com.alibaba.fastjson.JSON;
import forum.pojo.*;
import forum.service.BackendService;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;


@MultipartConfig
@WebServlet("/backend/*")
public class BackendServlet extends BaseServlet {
    BackendService backendService = new BackendService();


    public void categoryList(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<Category> categories = backendService.categoryList();
        String J_categories = JSON.toJSONString(categories);
        responseJOSN(response, J_categories);
    }

    public void categoryAdd(HttpServletRequest request, HttpServletResponse response) {
        String newCategory = request.getParameter("newCategory");
        Category category = new Category();
        category.setCategory(newCategory);
        boolean add = backendService.categoryAdd(category);
        responseJOSN(response, String.valueOf(add));
    }

    public void postSearch(HttpServletRequest request, HttpServletResponse response) {
        String idsStr = request.getParameter("ids");
        String cleanIdsStr = idsStr.replaceAll("\"", "");
        String[] idsArray = cleanIdsStr.substring(1, cleanIdsStr.length() - 1).split(",");
        ArrayList<Integer> ids = new ArrayList<>();
        for (String id : idsArray) {
            ids.add(Integer.parseInt(id.trim()));
        }
        System.out.println(ids);
        ArrayList<Post> postsByIds = backendService.getPostsByIds(ids);
        String J = JSON.toJSONString(postsByIds);
        responseJOSN(response, J);
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("backend/delete");
        int postId = Integer.parseInt(request.getParameter("postId"));
        boolean delete = backendService.delete(postId);
        responseJOSN(response, String.valueOf(delete));
    }
}





