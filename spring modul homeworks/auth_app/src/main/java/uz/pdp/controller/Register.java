package uz.pdp.controller;

import uz.pdp.model.Result;
import uz.pdp.model.User;
import uz.pdp.service.DbService;

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
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String userName = req.getParameter("userName");
        String phoneNumber = req.getParameter("phoneNumber");
        String password = req.getParameter("password");
        String prePassword = req.getParameter("prePassword");

        PrintWriter printWriter = resp.getWriter();
        if (password.equals(prePassword)){
            DbService dbService = new DbService();
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setUserName(userName);
            user.setPhoneNumber(phoneNumber);
            user.setPassword(password);


            Result result = dbService.registerUser(user);
            if (result.isSuccess()){

                printWriter.write("<h1 color='green'>"+result.getMessage()+"</h1>");
            }else {
                printWriter.write("<h1 color='red'>"+result.getMessage()+"</h1>");
            }
        } else {
            printWriter.write("<h1 color='red'>Passwords not equal</h1>");
        }





    }
}
