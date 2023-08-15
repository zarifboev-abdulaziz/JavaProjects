package uz.pdp.controller.forUser;

import uz.pdp.controller.auth.Security;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/userHome")
public class UserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        boolean checkCookies = Security.checkCookies(req);

        if (checkCookies){
            req.getRequestDispatcher("/jsp/userHome.jsp").include(req, resp);
        } else {
            printWriter.print("<h2>You should Login first</h2>");
            req.getRequestDispatcher("/jsp/login.jsp").include(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
