package uz.pdp.controller.forTask;

import uz.pdp.dao.TaskDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/deleteTask")
public class DeleteTask extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sid = req.getParameter("id");
        int taskId = Integer.parseInt(sid);
        boolean isDeleted = TaskDao.deleteTask(taskId);

        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();

        if (isDeleted){
            resp.sendRedirect("/showUserTasks");
        } else {
            printWriter.print("<h2>Failed to edit</h2>");
            printWriter.print("<a href=\"/jsp/userHome.jsp\"><h4> Back to home </h4></a><hr>");
        }
    }

}
