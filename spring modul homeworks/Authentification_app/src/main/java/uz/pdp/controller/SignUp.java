package uz.pdp.controller;

import uz.pdp.model.Person;
import uz.pdp.service.DBService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/signup")
public class SignUp extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("signup.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");

        PrintWriter printWriter = resp.getWriter();
        DBService dbService = new DBService();
        Person person = dbService.signUp(userName, password);
        if (person == null){
            printWriter.print("Bunday User mavjud emas");
        } else {
            resp.sendRedirect("/cabinet");
        }
    }
}
