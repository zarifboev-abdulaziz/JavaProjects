package uz.pdp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect implements Constants{

    public static Connection getConnnection() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);

            return connection;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }



}
