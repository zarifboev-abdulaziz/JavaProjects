package uz.pdp.controller;

import uz.pdp.model.User;
import uz.pdp.service.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
        String login = req.getParameter("login");
        String parol = req.getParameter("parol");

        for (User user : DB.userList) {
            if (user.getLogin().equals(login) && user.getParol().equals(parol)){
                resp.sendRedirect("/cabinet");
            }
        }

        PrintWriter printWriter = resp.getWriter();
        resp.setContentType("text/html");
        printWriter.println("<h2>Invalid User or password </h2><br>");
        printWriter.println("<a href=\"index.html\">Back to home</a>");

    }
}
