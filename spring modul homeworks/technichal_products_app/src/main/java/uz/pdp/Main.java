package uz.pdp;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        DatabaseService databaseService = new DatabaseService();
        databaseService.showDifferentProducts();
    }
}
