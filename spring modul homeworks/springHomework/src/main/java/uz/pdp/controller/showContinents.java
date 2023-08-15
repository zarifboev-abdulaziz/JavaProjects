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

@WebServlet("/showContinents")
public class showContinents extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBService dbService = new DBService();
        List<Continent> allContinents = dbService.getAllContinents();

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/read/show-continents.jsp");
        req.setAttribute("continents", allContinents);

        requestDispatcher.forward(req, resp);


//        resp.setContentType("text/html");
//        printWriter.println("<h3>Continent list</h3><p></p>");
//
//
//        printWriter.print("<table border='1' width='50%'");
//        printWriter.print("<tr><th>Continent Name</th><th>Edit</th><th>Delete</th></tr>");
//
//        for (Continent continent : allContinents) {
//            printWriter.println("<tr><td>" + continent.getName() + "</td><td>" +
//                    "<a href = '/updateContinent?id="+continent.getId()+"'>edit</a></td><td><a href = " +
//                    "'/deleteContinent?id="+continent.getId()+"'>delete</a></td></tr>");
//        }
//
//        printWriter.print("</table><br>");
//        printWriter.println("<a href ='/home'>Go to home</a><br>");


    }
}
