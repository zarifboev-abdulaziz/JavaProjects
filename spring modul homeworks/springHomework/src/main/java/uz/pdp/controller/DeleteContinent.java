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

@WebServlet("/deleteContinent")
public class DeleteContinent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sid = req.getParameter("id");
        int id = Integer.parseInt(sid);

        PrintWriter printWriter = resp.getWriter();
        resp.setContentType("text/html");



        DBService dbService = new DBService();
        boolean isOk = dbService.deleteContinent(id);

        if (isOk){
            printWriter.println("<h2>Successfully Deleted</h2>");
            printWriter.println("<a href = \"/home\">Back to home</a>");
        } else {
            printWriter.println("<h3>You cannot delete this continent</h3>");

            List<Continent> allContinents = dbService.getAllContinents();

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/read/show-continents.jsp");
            req.setAttribute("continents", allContinents);

            requestDispatcher.include(req, resp);
        }
    }
}

