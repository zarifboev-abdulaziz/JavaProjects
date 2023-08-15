package uz.pdp.controller.forTask;

import uz.pdp.dao.TaskDao;
import uz.pdp.model.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

@WebServlet("/addTask")
public class AddTask extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/jsp/addTask.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        LocalDateTime deadline = LocalDateTime.parse(req.getParameter("deadline"));
        LocalDateTime createdAt = LocalDateTime.parse(req.getParameter("created_at"));
        HttpSession session = req.getSession(false);
        int userId = (int)session.getAttribute("userId");

        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setDeadline(deadline);
        task.setCreated_at(createdAt);
        task.setUserId(userId);

        boolean isAdded = TaskDao.addTask(task);

        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();

        if (isAdded){
            printWriter.print("<h2>Successfully Added</h2>");
            printWriter.print("<a href=\"/jsp/userHome.jsp\"><h4> Back to home </h4></a><hr>");
        } else {
            printWriter.print("<h2>Failed to Add</h2>");
            printWriter.print("<a href=\"/jsp/userHome.jsp\"><h4> Back to home </h4></a><hr>");
        }
    }

}
