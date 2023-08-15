package uz.pdp.dao;

import uz.pdp.model.Task;
import uz.pdp.model.User;
import uz.pdp.util.DbConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public static List<User> getAllUsers(){
        Connection connnection = DbConnect.getConnnection();
        List<User> userList = new ArrayList<>();
        try {

            PreparedStatement preparedStatement = connnection.prepareStatement("Select * from users");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String email = resultSet.getString(3);
                String password = resultSet.getString(3);
                double balance = resultSet.getDouble(6);
                Timestamp created_at = resultSet.getTimestamp(6);
                Timestamp updated_at = resultSet.getTimestamp(7);

                User user = new User(id, firstName, lastName, email, password, balance,
                        created_at.toLocalDateTime(), updated_at.toLocalDateTime());
                userList.add(user);
            }

            return userList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean saveUser(User user){
        Connection connnection = DbConnect.getConnnection();

        try {
            String query = "INSERT INTO  users (first_name, last_name, email, password) VALUES (?, ?, ?, ?);";

            PreparedStatement preparedStatement = connnection.prepareStatement(query);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.execute();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static User isUserExists(User inputUser){
        Connection connnection = DbConnect.getConnnection();

        try {
            String query = "SELECT * from users where email =? AND password =? ;";

            PreparedStatement preparedStatement = connnection.prepareStatement(query);
            preparedStatement.setString(1, inputUser.getEmail());
            preparedStatement.setString(2, inputUser.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String email = resultSet.getString(4);
                String password = resultSet.getString(5);
                double balance = resultSet.getDouble(6);
                Timestamp created_at = resultSet.getTimestamp(7);
                Timestamp updated_at = resultSet.getTimestamp(8);

                User user = new User(id, firstName, lastName, email, password, balance,
                        created_at.toLocalDateTime(), updated_at.toLocalDateTime());
                return user;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new User();
    }

    public static boolean updateUser(User user){
        Connection connnection = DbConnect.getConnnection();
        try {

            PreparedStatement preparedStatement = connnection.prepareStatement("UPDATE users set first_name = ?, last_name = ?, email  = ?, password = ? where id = ?;");
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, user.getId());
            preparedStatement.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
