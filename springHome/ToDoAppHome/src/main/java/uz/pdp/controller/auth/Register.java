package uz.pdp.controller.auth;


import uz.pdp.dao.UserDao;
import uz.pdp.model.User;

import javax.servlet.RequestDispatcher;
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
        resp.sendRedirect("/jsp/register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);

        boolean isSaved = UserDao.saveUser(user);
        RequestDispatcher requestDispatcher = null;
        PrintWriter printWriter = resp.getWriter();
        resp.setContentType("text/html");

        if (isSaved){
            printWriter.println("<h1>Successfully Registered, Now you can login through " +
                    "your email</h1>");
            requestDispatcher = req.getRequestDispatcher("home.jsp");
            requestDispatcher.include(req, resp);
        } else {
            printWriter.println("<h1>Failed to register, Please try again</h1>");
            requestDispatcher = req.getRequestDispatcher("/jsp/register.jsp");
            requestDispatcher.include(req, resp);
        }

    }
}
