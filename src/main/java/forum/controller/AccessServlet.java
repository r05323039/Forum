package forum.controller;

import forum.dao.AccessDao;
import forum.pojo.Access;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
@WebServlet("/access/*")
public class AccessServlet extends BaseServlet {
    private AccessDao accessDao = new AccessDao();

    public void access(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        System.out.println("AccessServlet");

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        System.out.println("AccessServlet: "+userName + "," + password);
        Access access = accessDao.login(userName, password);
        if (access != null) {
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(60 * 60 * 3);
            session.setAttribute("access", access);
            Cookie cookie = new Cookie("sessionId", session.getId());
            cookie.setMaxAge(60 * 60 * 24 * 7);
            response.addCookie(cookie);
            response.sendRedirect("http://localhost:8080/elitebaby/forum/home");
            System.out.println("登入成功");
        } else {
            System.out.println("登入失敗");
        }
    }
}
