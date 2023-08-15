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
import java.util.List;

@WebServlet("/tasks")
public class TaskController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Task> allTasks = TaskDao.getAllTasks();
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/view-tasks.jsp");
        req.setAttribute("taskList", allTasks);
        requestDispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
