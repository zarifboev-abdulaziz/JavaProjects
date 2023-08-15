package uz.pdp.controller.forUser;

import uz.pdp.dao.UserDao;
import uz.pdp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/editUserProfile")
public class EditUserProfile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/jsp/editUserProfile.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        HttpSession session = req.getSession(false);
        int userId = (int)session.getAttribute("userId");

        User user = new User();
        user.setId(userId);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);

        boolean isAdded = UserDao.updateUser(user);

        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();

        if (isAdded){
            printWriter.print("<h2>Successfully Edited</h2>");
            printWriter.print("<a href=\"/jsp/userHome.jsp\"><h4> Back to home </h4></a><hr>");
        } else {
            printWriter.print("<h2>Failed to edit</h2>");
            printWriter.print("<a href=\"/jsp/userHome.jsp\"><h4> Back to home </h4></a><hr>");
        }
    }
}
