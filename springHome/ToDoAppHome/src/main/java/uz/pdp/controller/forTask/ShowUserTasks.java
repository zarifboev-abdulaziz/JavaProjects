package uz.pdp.controller.forTask;

import uz.pdp.dao.TaskDao;
import uz.pdp.model.Task;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/showUserTasks")
public class ShowUserTasks extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Integer userId = (int)session.getAttribute("userId");


        List<Task> userTasks = TaskDao.getUserTasks(userId);

        req.setAttribute("taskList", userTasks);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/showTasks.jsp");
        requestDispatcher.forward(req, resp);

    }
}
