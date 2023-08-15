package uz.pdp.dao;

import uz.pdp.model.Task;
import uz.pdp.model.User;
import uz.pdp.util.DbConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDao {

    public static List<Task> getAllTasks(){
        Connection connnection = DbConnect.getConnnection();
        List<Task> taskList = new ArrayList<>();
        try {

            PreparedStatement preparedStatement = connnection.prepareStatement("Select * from tasks order by deadline asc");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String title = resultSet.getString(2);
                String description = resultSet.getString(3);
                boolean status = resultSet.getBoolean(4);
                Timestamp deadline = resultSet.getTimestamp(5);
                Timestamp created_at = resultSet.getTimestamp(6);
                Timestamp updated_at = resultSet.getTimestamp(7);

                Task task = new Task(id, title, description, status, deadline.toLocalDateTime(), created_at.toLocalDateTime(), updated_at.toLocalDateTime());
                taskList.add(task);
            }

            return taskList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Task> getUserTasks(int userId){
        Connection connnection = DbConnect.getConnnection();
        List<Task> taskList = new ArrayList<>();
        try {

            PreparedStatement preparedStatement = connnection.prepareStatement("Select * from tasks where user_id = "+userId+" order by deadline asc");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String title = resultSet.getString(2);
                String description = resultSet.getString(3);
                boolean status = resultSet.getBoolean(4);
                Timestamp deadline = resultSet.getTimestamp(5);
                Timestamp created_at = resultSet.getTimestamp(6);
                Timestamp updated_at = resultSet.getTimestamp(7);
                int user_id = resultSet.getInt(8);

                Task task = new Task(id, title, description, status, deadline.toLocalDateTime(),
                        created_at.toLocalDateTime(), updated_at.toLocalDateTime(), user_id);
                taskList.add(task);
            }

            return taskList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Task getTaskById(int taskId){
        Connection connnection = DbConnect.getConnnection();
        try {

            PreparedStatement preparedStatement = connnection.prepareStatement("Select * from tasks where id = "+taskId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String title = resultSet.getString(2);
                String description = resultSet.getString(3);
                boolean status = resultSet.getBoolean(4);
                Timestamp deadline = resultSet.getTimestamp(5);
                Timestamp created_at = resultSet.getTimestamp(6);
                Timestamp updated_at = resultSet.getTimestamp(7);
                int user_id = resultSet.getInt(8);

                Task task = new Task(id, title, description, status, deadline.toLocalDateTime(),
                        created_at.toLocalDateTime(), updated_at.toLocalDateTime(), user_id);
                return task;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean updateTask(Task task){
        Connection connnection = DbConnect.getConnnection();
        try {

            PreparedStatement preparedStatement = connnection.prepareStatement("UPDATE tasks set title = ?, description = ?, deadline = ?, created_at = ? where id = ?;");
            preparedStatement.setString(1, task.getTitle());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(task.getDeadline()));
            preparedStatement.setTimestamp(4, Timestamp.valueOf(task.getCreated_at()));
            preparedStatement.setInt(5, task.getId());
            preparedStatement.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean addTask(Task task){
        Connection connnection = DbConnect.getConnnection();
        try {

            PreparedStatement preparedStatement = connnection.prepareStatement("INSERT INTO " +
                    "tasks (title, description, deadline, created_at, user_id) VALUES (?, ?, ?, " +
                    "?, ?)");

            preparedStatement.setString(1, task.getTitle());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(task.getDeadline()));
            preparedStatement.setTimestamp(4, Timestamp.valueOf(task.getCreated_at()));
            preparedStatement.setInt(5, task.getUserId());
            preparedStatement.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean deleteTask(int taskId){
        Connection connnection = DbConnect.getConnnection();
        try {

            PreparedStatement preparedStatement = connnection.prepareStatement("DELETE FROM " +
                    "tasks where id = "+taskId);
            preparedStatement.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
