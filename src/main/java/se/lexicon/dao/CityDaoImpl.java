package se.lexicon.dao;

import se.lexicon.db.DBConnection;
import se.lexicon.model.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Represents the implementation of CityDao for interacting with the 'city' table in the database.
 */
public class CityDaoImpl implements CityDao {

    private City mapRow(ResultSet resultSet) throws SQLException {
        return new City(
                resultSet.getInt("ID"),
                resultSet.getString("Name"),
                resultSet.getString("CountryCode"),
                resultSet.getString("District"),
                resultSet.getInt("Population")
        );
    }

    @Override
    public Optional<City> findById(int id) throws SQLException {
        String query = "SELECT * FROM city WHERE ID = ?";
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(mapRow(resultSet));
            } else {
                return Optional.empty();
            }
        }
    }

    @Override
    public List<City> findByCode(String code) throws SQLException {
        String query = "SELECT * FROM city WHERE CountryCode = ?";
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, code);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<City> cities = new ArrayList<>();

            while (resultSet.next()) {
                City city = mapRow(resultSet);
                cities.add(city);
            }

            return cities;
        }
    }

    @Override
    public List<City> findByName(String name) throws SQLException {
        String query = "SELECT * FROM city WHERE Name = ?";
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<City> cities = new ArrayList<>();

            while (resultSet.next()) {
                City city = mapRow(resultSet);
                cities.add(city);
            }

            return cities;
        }
    }

    @Override
    public List<City> findAll() throws SQLException {
        String query = "SELECT * FROM city";
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<City> cities = new ArrayList<>();

            while (resultSet.next()) {
                City city = mapRow(resultSet);
                cities.add(city);
            }

            return cities;
        }
    }

    @Override
    public City save(City city) throws SQLException {
        String query = "INSERT INTO city (Name, CountryCode, District, Population) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, city.getName());
            preparedStatement.setString(2, city.getCountryCode());
            preparedStatement.setString(3, city.getDistrict());
            preparedStatement.setInt(4, city.getPopulation());

            int rowsAffected  = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("City saved successfully");
            }
            else {
                System.out.println("Failed to save City");
            }
        }

        return city;
    }

    @Override
    public void update(City city) throws SQLException {
        String query = "UPDATE city SET Name = ?, CountryCode = ?, District = ?, Population = ? WHERE ID = ?";
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, city.getName());
            preparedStatement.setString(2, city.getCountryCode());
            preparedStatement.setString(3, city.getDistrict());
            preparedStatement.setInt(4, city.getPopulation());
            preparedStatement.setInt(5, city.getId());

            int rowsAffected  = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("City updated successfully");
            }
            else {
                System.out.println("Failed to update City");
            }
        }
    }

    @Override
    public void deleteById(int id) throws SQLException {
        String query = "DELETE FROM city WHERE ID = ?";
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("City deleted successfully");
            } else
                System.out.println("Failed to delete City");
        }
    }
}
