package uz.pdp.controller.auth;

import uz.pdp.dao.UserDao;
import uz.pdp.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/jsp/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        User userExists = UserDao.isUserExists(user);


        RequestDispatcher requestDispatcher = null;
        PrintWriter printWriter = resp.getWriter();
        resp.setContentType("text/html");

        if (userExists.getId() != null){
            HttpSession session = req.getSession();
            session.setAttribute("userId", userExists.getId());

            Cookie cookie = new Cookie("userId", userExists.getId().toString());
            resp.addCookie(cookie);

            resp.sendRedirect("/userHome");

        } else {
            printWriter.println("<h1>Failed to login, Please try again</h1>");
            requestDispatcher = req.getRequestDispatcher("/jsp/login.jsp");
            requestDispatcher.include(req, resp);
        }
    }
}
