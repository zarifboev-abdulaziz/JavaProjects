package uz.pdp.service;

import uz.pdp.model.Result;
import uz.pdp.model.User;

import javax.jws.soap.SOAPBinding;
import java.sql.*;

public class DbService {
    String url = "jdbc:postgresql://localhost:5432/my_employee";
    String dbUser = "postgres";
    String dbPassword = "root123";

    public Result registerUser(User user){
        try {
            Class.forName("org.postgresql.Driver");
            //Db ga ulaninsh uchun connection yozdik
            Connection connection = DriverManager.getConnection(url, dbUser, dbPassword);
            //query yuborish uchun statement ochdik
            Statement statement = connection.createStatement();

            //Phone numberni tekshirish
            String checkPhoneNumber =
                    "Select count(*) from users where phone_number = '" + user.getPhoneNumber() + "'";
            ResultSet resultSet = statement.executeQuery(checkPhoneNumber);

            int countUserByPhoneNumber = 0;
            while (resultSet.next()){
                countUserByPhoneNumber = resultSet.getInt(1);
            }

            if (countUserByPhoneNumber > 0){
                return new Result("Phone Number already exists", false);
            }

            String checkUserNameQuery =
                    "Select count(*) from users where user_name = '" + user.getUserName() + "'";

            ResultSet resultSet1 = statement.executeQuery(checkUserNameQuery);
            int countUserByFields = 0;
            while (resultSet1.next()) {
                countUserByFields = resultSet1.getInt(1);
            }

            if(countUserByFields > 0) {
                return new Result("User Name already exists", false);
            }

            String query = "INSERT INTO users(first_name, last_name, user_name, phone_number, " +
                    "password) VALUES ('"+user.getFirstName()+"', '"+user.getLastName()+"', '"+
                    user.getUserName()+ "', '"+user.getPhoneNumber()+"', '"+user.getPassword()+"');";
            boolean execute = statement.execute(query);

            return new Result("Succesfully Registered", true);


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return new Result("Error in server", false);
        }

    }

    public User login(String userName, String password){
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection= DriverManager.getConnection(url, dbUser, dbPassword);

            String query = "Select * from users where user_name =? AND password =?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int id = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String user_name = resultSet.getString(4);
                String phoneNumber = resultSet.getString(5);

                User user = new User(id,
                        firstName,
                        lastName,
                        user_name,
                        phoneNumber,
                        null
                );
                return user;

            }
            return null;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public User loadUserByCookie(String userName){
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection= DriverManager.getConnection(url, dbUser, dbPassword);

            String query = "Select * from users where user_name =?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int id = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String user_name = resultSet.getString(4);
                String phoneNumber = resultSet.getString(5);

                User user = new User(id,
                        firstName,
                        lastName,
                        user_name,
                        phoneNumber,
                        null
                );
                return user;

            }
            return null;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }


}
