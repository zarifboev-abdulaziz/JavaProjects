package uz.pdp;

import java.sql.*;
import java.util.Scanner;

public class DatabaseService {
    String mobt = "postgresql";
    String host = "localhost";
    String port = "5432";
    String db = "computer_db";
    String dbUSer = "postgres";
    String password = "root123";

    String url = "jdbc:" + mobt + "://" + host + ":" + port + "/" + db;

    public void showAllProducts() throws SQLException {
        Connection connection = DriverManager.getConnection(url, dbUSer, password);
        Statement statement = connection.createStatement();

        String query = "Select * from product";
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            System.out.print("Maker: " + resultSet.getString(1));
            System.out.print(", model: " + resultSet.getString(2));
            System.out.println(", type: " + resultSet.getString(3));
        }
        resultSet.close();
        statement.close();
        connection.close();

    }

    public void showAllProductsWithPrice() throws SQLException {
        Connection connection = DriverManager.getConnection(url, dbUSer, password);
        Statement statement = connection.createStatement();

        String query = "with cte as (\n" +
                "    select maker, p.model, price\n" +
                "    from pc\n" +
                "             join product p on p.model = pc.model\n" +
                "    UNION\n" +
                "    select maker, p2.model, price\n" +
                "    from laptop\n" +
                "             join product p2 on laptop.model = p2.model\n" +
                "    UNION\n" +
                "    select maker, p3.model, price\n" +
                "    from printer\n" +
                "             join product p3 on p3.model = printer.model\n" +
                ")\n" +
                "select * from cte order by price\n" +
                ";";
        ResultSet resultSet = statement.executeQuery(query);

        int i = 1;
        while (resultSet.next()) {
            System.out.print(i + ". Maker: " + resultSet.getString(1));
            System.out.print(", model: " + resultSet.getString(2));
            System.out.println(", price: " + resultSet.getDouble(3));
            i++;
        }
        resultSet.close();
        statement.close();
        connection.close();

    }

    public void showDifferentProducts() throws SQLException {
        Connection connection = DriverManager.getConnection(url, dbUSer, password);
        Statement statement = connection.createStatement();

        String query = "";

        System.out.println("0=> Exit 1=> Show pc 2=> show laptops 3=> show printers");

        switch (new Scanner(System.in).nextInt()){
            case 0: return;
            case 1:
                query = "select maker, p.model, price\n" +
                        "from pc\n" +
                        "join product p on p.model = pc.model";
                break;
            case 2:
                query = "    select maker, p2.model, price\n" +
                        "    from laptop\n" +
                        "    join product p2 on laptop.model = p2.model";
                break;
            case 3:
                query = "    select maker, p3.model, price\n" +
                        "    from printer\n" +
                        "    join product p3 on p3.model = printer.model";
                break;
        }

        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            System.out.print("Maker: " + resultSet.getString(1));
            System.out.print(", model: " + resultSet.getString(2));
            System.out.println(", type: " + resultSet.getString(3));
        }
        resultSet.close();
        statement.close();
        connection.close();

        showDifferentProducts();
    }

    public void addProduct() throws SQLException {
        Connection connection = DriverManager.getConnection(url, dbUSer, password);

        System.out.println("Enter product maker:");
        String maker = new Scanner(System.in).nextLine();
        System.out.println("Enter product model:");
        String model = new Scanner(System.in).nextLine();
        System.out.println("Enter product type:");
        String type = new Scanner(System.in).nextLine();

        if (checkModel(model)) {
            System.out.println("Failed. Model must be unique");
            return;
        }

        String query = "INSERT INTO product(maker, model, type) VALUES (?, ?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, maker);
        preparedStatement.setString(2, model);
        preparedStatement.setString(3, type);

        preparedStatement.execute();
        System.out.println("Successfully Added");

        preparedStatement.close();
        preparedStatement.close();
        connection.close();
    }

    public boolean checkModel(String model) throws SQLException {
        Connection connection = DriverManager.getConnection(url, dbUSer, password);
        Statement statement = connection.createStatement();

        String query = "Select * from product";
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            String existingModel = resultSet.getString(2);
            if (existingModel.equals(model)){
                return true;
            }
        }
        resultSet.close();
        statement.close();
        connection.close();
        return false;
    }

    public void showAllProductsBetweenPrice() throws SQLException {
        Connection connection = DriverManager.getConnection(url, dbUSer, password);
        Statement statement = connection.createStatement();

        System.out.println("Enter max amount:");
        double maxPrice = new Scanner(System.in).nextDouble();
        System.out.println("Enter min amount:");
        double minPrice = new Scanner(System.in).nextDouble();

        String query = "with cte as (\n" +
                "    select maker, p.model, price\n" +
                "    from pc\n" +
                "    join product p on p.model = pc.model\n" +
                "    UNION\n" +
                "    select maker, p2.model, price\n" +
                "    from laptop\n" +
                "    join product p2 on laptop.model = p2.model\n" +
                "    UNION\n" +
                "    select maker, p3.model, price\n" +
                "    from printer\n" +
                "             join product p3 on p3.model = printer.model\n" +
                ")\n" +
                "   select * from cte where price between "+ minPrice +" And "+ maxPrice +" " +
                "order by " +
                "price\n" +
                ";";

        ResultSet resultSet = statement.executeQuery(query);

        int i = 1;
        while (resultSet.next()) {
            System.out.print(i + ". Maker: " + resultSet.getString(1));
            System.out.print(", model: " + resultSet.getString(2));
            System.out.println(", price: " + resultSet.getDouble(3));
            i++;
        }
        resultSet.close();
        statement.close();
        connection.close();

    }

}
