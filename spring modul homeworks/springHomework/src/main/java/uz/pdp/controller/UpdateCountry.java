package uz.pdp.controller;

import uz.pdp.model.Continent;
import uz.pdp.service.DBService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/updateCountry")
public class UpdateCountry extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        resp.setContentType("text/html");
        printWriter.print("<h2>Update Country</h2>");
        String id = req.getParameter("id");


        printWriter.println("<form action=\"/updateCountry\" method=\"post\">\n" +
                "<input type=\"hidden\" name=\"id\" value="+id+"><br>");
        printWriter.print("<select name='continent' style='width:150px'>");

        List<Continent> allContinents = new DBService().getAllContinents();

        for (Continent allContinent : allContinents) {
        printWriter.print("<option value=\""+ allContinent.getId() + "\">" + allContinent.getName() + "</option>");
        }

        printWriter.print("<option>Other</option>");
        printWriter.print("</select>");

        printWriter.println(
                "    <input type=\"text\" placeholder=\"Enter new name\" name=\"name\"><br>\n" +
                "    <button>Edit & Save</button>\n" +
                "</form>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sid = req.getParameter("id");
        int id = Integer.parseInt(sid);
        String continent = req.getParameter("continent");
        int continentId = Integer.parseInt(continent);
        String countryName = req.getParameter("name");

        DBService service = new DBService();
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        boolean isOK = service.updateCountry(id, continentId, countryName);

        if (isOK){
            writer.println("<h2>Successfully Edited</h2>");
            writer.println("<a href = \"/home\">Back to home</a>");
        } else {
            writer.println("<h2>Failed to edit</h2>");
            writer.println("<a href = \"/home\">Back to home</a>");
        }
    }
}
