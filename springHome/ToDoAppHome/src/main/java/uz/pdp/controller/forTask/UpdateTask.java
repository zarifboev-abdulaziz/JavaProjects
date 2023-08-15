package uz.pdp.controller.forTask;

import uz.pdp.dao.TaskDao;
import uz.pdp.model.Task;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

@WebServlet("/updateTask")
public class UpdateTask extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sid = req.getParameter("id");
        int taskId = Integer.parseInt(sid);
        Task taskById = TaskDao.getTaskById(taskId);

        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();


        printWriter.print("<h1>Update Task</h1><hr>");
        printWriter.print("<form action=\"/updateTask\" method=\"post\">");

        printWriter.print("<input type=\"hidden\" value=\""+taskId+"\" name=\"id\"><br>");
        printWriter.print("<input type=\"text\" value=\""+taskById.getTitle()+"\" name=\"title\"><hr>");
        printWriter.print("<input type=\"text\" value=\""+taskById.getDescription()+"\" name=\"description\"><hr>");
        printWriter.print("<input type=\"datetime-local\" value=\""+taskById.getDeadline()+"\" name=\"deadline\"><hr>");
        printWriter.print("<input type=\"datetime-local\" value=\""+taskById.getCreated_at()+"\" name=\"createdAt\"><hr>");
        printWriter.print("<button type\"submit\">Submit</button>");

        printWriter.print("</form>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sid = req.getParameter("id");
        int id = Integer.parseInt(sid);
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        LocalDateTime deadline = LocalDateTime.parse(req.getParameter("deadline"));
        LocalDateTime createdAt = LocalDateTime.parse(req.getParameter("createdAt"));

        Task task = new Task();
        task.setId(id);
        task.setTitle(title);
        task.setDescription(description);
        task.setDeadline(deadline);
        task.setCreated_at(createdAt);

        boolean isEdited = TaskDao.updateTask(task);

        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();

        if (isEdited){
            printWriter.print("<h2>Successfully Edited</h2>");
            printWriter.print("<a href=\"/jsp/userHome.jsp\"><h4> Back to home </h4></a><hr>");
        } else {
            printWriter.print("<h2>Failed to edit</h2>");
            printWriter.print("<a href=\"/jsp/userHome.jsp\"><h4> Back to home </h4></a><hr>");
        }
    }

}
