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

@WebServlet("/register")
public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("register.html");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String parol = req.getParameter("parol");

        DB.userList.add(new User(login, parol));

        PrintWriter printWriter = resp.getWriter();
        resp.setContentType("text/html");
        printWriter.println("<h2>Successfully Registered</h2><br>");

        printWriter.println("<a href =\"/login\">Login</a>");
    }
}
