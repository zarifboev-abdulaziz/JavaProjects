package uz.pdp.controller.auth;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        session.invalidate();

        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("userId")){
                Cookie cookie1 = new Cookie("userId", "");
                cookie1.setMaxAge(0);
                resp.addCookie(cookie1);
            }
        }

        resp.sendRedirect("/home.jsp");
    }

}
