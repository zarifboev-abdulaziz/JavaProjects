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

@WebServlet("/login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("login.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");

        DbService service = new DbService();
        User user = service.login(userName, password);

        PrintWriter printWriter = resp.getWriter();
        if (user == null){
            printWriter.write("<h1>Login or Password not found</h1>");
        } else {
            Cookie cookie = new Cookie("AuthApp", user.getUserName());
            cookie.setMaxAge(60);
            resp.addCookie(cookie);
            resp.sendRedirect("/cabinet");

        }

    }
}
