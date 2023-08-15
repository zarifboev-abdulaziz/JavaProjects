package uz.pdp.controller;

import uz.pdp.model.Continent;
import uz.pdp.model.Country;
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

@WebServlet("/showCountries")
public class ShowCountries extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        DBService dbService = new DBService();
        Integer page = Integer.parseInt(req.getParameter("page"));

        List<Country> allCountries = dbService.getAllCountries(page);
        req.setAttribute("countries", allCountries);

        resp.setContentType("text/html");
        printWriter.println("<h3>Country list</h3><p></p>");


        printWriter.print("<table border='1' width='50%'");
        printWriter.print("<tr><th>Continent Name</th><th>Country Name</th><th>Edit</th><th>Delete</th></tr>");

        for (Country country : allCountries) {
            printWriter.println("<tr><td>" + country.getContinentName() + "</td><td>" + country.getName() + "</td><td>"+
                    "<a href = '/updateCountry?id="+country.getId()+"'>edit</a></td><td><a href " +
                    "= " +
                    "'/deleteCountry?id="+country.getId()+"'>delete</a></td></tr>");
        }

        printWriter.print("</table><br>");

        int totalCountry = dbService.getTotalCountry();
        if (totalCountry%5==0){
            totalCountry = totalCountry/5;
        }else {
            totalCountry = totalCountry/5 + 1;
        }

//        req.setAttribute("pageCount", totalCountry);
//
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/read/show-countries.jsp");
//        requestDispatcher.forward(req, resp);

        for (int i = 1; i <= totalCountry; i++) {
            printWriter.print("<a href='showCountries?page="+i+"'>"+i+"</a> ");
        }
        printWriter.println("<br>");
        printWriter.println("<a href ='/home'>Go to home</a><br>");



    }
}
