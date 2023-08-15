package uz.pdp.controller;

import uz.pdp.model.Person;
import uz.pdp.service.DBService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("login.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String fullName = req.getParameter("fullName");
        String password = req.getParameter("password");
        String prePassword = req.getParameter("prePassword");

        if (!password.equals(prePassword)){
            resp.getWriter().write("Parol xato");
        }
        if (password.length() < 8){
            resp.getWriter().write("Parol kamida 8ta belgi bo'lishi kerak");
        }
        DBService dbService = new DBService();
        boolean isOK = dbService.saveUser(new Person(fullName, userName, password));
        if (isOK){
            resp.getWriter().write("Successfully Recorded");
        } else {
            resp.getWriter().write("Failed, Please try again");
        }


    }
}
