package uz.pdp.service;

import uz.pdp.model.Continent;
import uz.pdp.model.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBService {
    String url = "jdbc:postgresql://localhost:5432/demo";
    String dbUser = "postgres";
    String password = "root123";

    public Connection getConnection(){
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, dbUser, password);

            return connection;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Continent> getAllContinents(){

        List<Continent> continentList = new ArrayList<>();

        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from continents");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);

                continentList.add(new Continent(id, name));

            }

            return continentList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Country> getAllCountries(int page){

        page = page -1;
        List<Country> countryList = new ArrayList<>();

        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select co.id, co.continent_id, c.name, co.name\n" +
                    "from countries co\n" +
                    "join continents c on c.id = co.continent_id limit 5 offset "+(page*5));

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int continentId = resultSet.getInt(2);
                String continentName = resultSet.getString(3);
                String name = resultSet.getString(4);

                countryList.add(new Country(id, continentId, continentName, name));

            }

            return countryList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateContinent(int inputId, String inputName){
        Connection connection = getConnection();

        try {

            String query = "Update continents set name =? where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, inputName);
            preparedStatement.setInt(2, inputId);
            preparedStatement.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateCountry(int inputId, int continentId, String continentName){
        Connection connection = getConnection();

        try {

            String query = "Update countries set continent_id =?, name =? where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, continentId);
            preparedStatement.setString(2, continentName);
            preparedStatement.setInt(3, inputId);
            preparedStatement.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteContinent(int inputId){
        Connection connection = getConnection();

        try {

            String hasCountry = "Select count(*) from countries where continent_id = "+inputId;
            PreparedStatement preparedStatement1 = connection.prepareStatement(hasCountry);
            ResultSet resultSet = preparedStatement1.executeQuery();
            int count = 0;
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }

            if (count != 0){
                return false;
            }

            String query = "DELETE from continents where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, inputId);
            preparedStatement.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getTotalCountry(){
        Connection connection = getConnection();

        try {

            String hasCountry = "Select count(*) from countries";
            PreparedStatement preparedStatement1 = connection.prepareStatement(hasCountry);
            ResultSet resultSet = preparedStatement1.executeQuery();
            int count = 0;
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }

            return count;



        } catch (SQLException e) {
            e.printStackTrace();
        }
       return 0;
    }


    public boolean deleteCountry(int inputId){
        Connection connection = getConnection();

        try {

            String query = "DELETE from countries where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, inputId);
            preparedStatement.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addContinent(String inputName){
        Connection connection = getConnection();

        try {

            String query = "INSERT INTO continents (name) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, inputName);
            preparedStatement.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addCountry(int continent_id, String inputName){
        Connection connection = getConnection();

        try {

            String query = "INSERT INTO countries (continent_id, name) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, continent_id);
            preparedStatement.setString(2, inputName);
            preparedStatement.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



}
