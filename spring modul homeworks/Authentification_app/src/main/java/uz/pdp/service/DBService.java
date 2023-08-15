package uz.pdp.service;

import uz.pdp.model.Person;

import java.sql.*;

public class DBService {
    String url = "jdbc:postgresql://localhost:5432/person_db";
    String dbUser = "postgres";
    String dbPassword = "root123";

    public boolean saveUser(Person person){
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, dbUser, dbPassword);
            Statement statement = connection.createStatement();

            String checkUserNameQuery =
                    "Select count(*) from persons where user_name = '" + person.getUserName() + "'";

            ResultSet resultSet1 = statement.executeQuery(checkUserNameQuery);
            int countUserByUserName = 0;
            while (resultSet1.next()) {
                countUserByUserName = resultSet1.getInt(1);
            }

            if(countUserByUserName > 0) {
                return false;
            }

            String query = "INSERT INTO persons(full_name, user_name, password) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, person.getFullName());
            preparedStatement.setString(2, person.getUserName());
            preparedStatement.setString(3, person.getPassword());
            preparedStatement.execute();
            return true;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Person signUp(String userName, String password){
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection= DriverManager.getConnection(url, dbUser, dbPassword);

            String query = "Select * from persons where user_name =? AND password =?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String fullName = resultSet.getString(2);
                String user_name = resultSet.getString(3);

                Person person = new Person(fullName, user_name, null);
                return person;

            }
            return null;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
