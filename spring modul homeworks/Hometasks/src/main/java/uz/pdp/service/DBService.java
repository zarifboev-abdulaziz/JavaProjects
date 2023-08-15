package uz.pdp.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBService {

    public static String url = "jdbc:postgresql://localhost:5432/computer_db";
    public static String dbUSer = "postgres";
    public static String password = "root123";


    public static Connection getConnection(){
        Connection connection = null;
        try {

            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, dbUSer, password);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
