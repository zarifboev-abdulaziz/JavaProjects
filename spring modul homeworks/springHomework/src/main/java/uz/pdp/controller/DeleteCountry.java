package uz.pdp.controller;

import uz.pdp.service.DBService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/deleteCountry")
public class DeleteCountry extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sid = req.getParameter("id");
        int id = Integer.parseInt(sid);

        DBService dbService = new DBService();
        boolean isOk = dbService.deleteCountry(id);

        PrintWriter printWriter = resp.getWriter();
        resp.setContentType("text/html");

        if (isOk){
            printWriter.println("<h2>Successfully Deleted</h2>");
            printWriter.println("<a href = \"/home\">Back to home</a>");
        } else {
            printWriter.println("<h2>Failed to delete</h2>");
            printWriter.println("<a href = \"/home\">Back to home</a>");
        }
    }
}
