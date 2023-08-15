package uz.pdp.controller;

import uz.pdp.service.DBService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addCountry")
public class AddCountry extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("addCountry.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sid = req.getParameter("country");
        int country_id = Integer.parseInt(sid);
        String name = req.getParameter("name");

        boolean isOk = new DBService().addCountry(country_id, name);

        PrintWriter printWriter = resp.getWriter();
        resp.setContentType("text/html");

        if (isOk){
            printWriter.println("<h2>Successfully Added</h2>");
            printWriter.println("<a href = \"/home\">Back to home</a>");
        } else {
            printWriter.println("<h2>Failed to added</h2>");
            printWriter.println("<a href = \"/home\">Back to home</a>");
        }
    }
}
