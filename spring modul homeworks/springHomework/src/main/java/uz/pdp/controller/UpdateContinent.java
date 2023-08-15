package uz.pdp.controller;

import uz.pdp.service.DBService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/updateContinent")
public class UpdateContinent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        resp.setContentType("text/html");
        printWriter.print("<h2>Update Continent</h2>");
        String id = req.getParameter("id");

        printWriter.println("<form action=\"/updateContinent\" method=\"post\">\n" +
                "<input type=\"hidden\" name=\"id\" value="+id+"><br>"+
                "    <input type=\"text\" placeholder=\"Enter new name\" name=\"name\"><br>\n" +
                "    <button>Edit & Save</button>\n" +
                "</form>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String stringId = req.getParameter("id");
        Integer id = Integer.parseInt(stringId);
        String name = req.getParameter("name");

        DBService dbService = new DBService();
        boolean isOK = dbService.updateContinent(id, name);

        PrintWriter printWriter = resp.getWriter();
        resp.setContentType("text/html");
        if (isOK){
            printWriter.println("<h2>Successfully Edited</h2>");
            printWriter.println("<a href = \"/home\">Back to home</a>");
        } else {
            printWriter.println("<h2>Failed to edit</h2>");
            printWriter.println("<a href = \"/home\">Back to home</a>");
        }

    }
}
