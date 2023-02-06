package forum.controller;

import login.User;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter({"/forum/likeclick","/123"})
public class ForumFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        System.out.println("Forum Filter");

        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        if(user != null){
            chain.doFilter(request,response);
        }else {
            response.sendRedirect("http://localhost:8080/elitebaby/login.jsp");
        }
    }
}
