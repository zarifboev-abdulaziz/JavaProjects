package uz.pdp;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        String dbUrl="jdbc:postgresql://localhost:5432/computer_db";

        try {
            Class.forName("org.postgresql.Driver");


            Connection connection = DriverManager.getConnection(dbUrl, "postgres", "root123");

            Statement statement = connection.createStatement();

            String sql = "select * from product;";

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                System.out.print("Maker: "  + resultSet.getString(1));
                System.out.print(", Model: " + resultSet.getString(2));
                System.out.println(", Type: " + resultSet.getString(3));
            }




        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
