package uz.pdp.controller;

import uz.pdp.model.User;
import uz.pdp.service.DbService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/cabinet")
public class Cabinet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        Cookie[] cookies = req.getCookies();
        String userName = "";
        if (cookies != null){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("AuthApp")){
                    userName = cookie.getValue();
                    break;
                }
            }
        }
        DbService dbService = new DbService();
        User user = dbService.loadUserByCookie(userName);

        if (user == null){
            Cookie cookie = new Cookie("AuthApp", "");
            cookie.setMaxAge(0);
            resp.addCookie(cookie);
            resp.sendRedirect("/");
        }

        if (user != null){
            printWriter.write("<h1>Welcome to cabinet</h1><br>");
            printWriter.write("<a href = '/logout'>Log Out</a>");
        }
    }
}
