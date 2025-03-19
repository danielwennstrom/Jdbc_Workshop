package se.lexicon.dao;

import se.lexicon.db.DBConnection;
import se.lexicon.model.City;
import se.lexicon.model.Country;
import se.lexicon.model.CountryLanguage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CountryDaoImpl implements CountryDao {
    private Country mapRow(ResultSet resultSet) throws SQLException {
        return new Country(
                resultSet.getString("Code"),
                resultSet.getString("Name"),
                resultSet.getString("Continent"),
                resultSet.getString("Region"),
                resultSet.getDouble("SurfaceArea"),
                resultSet.getInt("IndepYear"),
                resultSet.getLong("Population"),
                resultSet.getDouble("LifeExpectancy"),
                resultSet.getDouble("GNP"),
                resultSet.getDouble("GNPOld"),
                resultSet.getString("LocalName"),
                resultSet.getString("GovernmentForm"),
                resultSet.getString("HeadOfState"),
                resultSet.getInt("Capital"),
                resultSet.getString("Code2")
        );
    }

    @Override
    public Country findByCode(String code) throws SQLException {
        String query = "SELECT * FROM country WHERE Code = ?";
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, code);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return mapRow(resultSet);
            } else {
                return null;
            }
        }
    }

    @Override
    public List<Country> findByName(String name) throws SQLException {
        String query = "SELECT * FROM country WHERE Name = ?";
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Country> countries = new ArrayList<>();

            while (resultSet.next()) {
                Country country = mapRow(resultSet);
                countries.add(country);
            }

            return countries;
        }
    }

    @Override
    public List<Country> findByContinent(String continent) throws SQLException {
        String query = "SELECT * FROM country WHERE Continent = ?";
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, continent);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Country> countries = new ArrayList<>();

            while (resultSet.next()) {
                Country country = mapRow(resultSet);
                countries.add(country);
            }

            return countries;
        }
    }

    @Override
    public List<Country> findByRegion(String region) throws SQLException {
        String query = "SELECT * FROM country WHERE Region = ?";
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, region);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Country> countries = new ArrayList<>();

            while (resultSet.next()) {
                Country country = mapRow(resultSet);
                countries.add(country);
            }

            return countries;
        }
    }

    @Override
    public List<Country> findByCapital(int capitalId) throws SQLException {
        String query = "SELECT * FROM country WHERE Capital = ?";
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, capitalId);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Country> countries = new ArrayList<>();

            while (resultSet.next()) {
                Country country = mapRow(resultSet);
                countries.add(country);
            }

            return countries;
        }
    }

    @Override
    public List<Country> findAll() throws SQLException {
        String query = "SELECT * FROM country";
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Country> countries = new ArrayList<>();

            while (resultSet.next()) {
                Country country = mapRow(resultSet);
                countries.add(country);
            }

            return countries;
        }
    }

    @Override
    public Country save(Country country) throws SQLException {
        String query = "INSERT INTO country (Code, Name, Continent, Region, SurfaceArea, IndepYear, Population," +
                " LifeExpectancy, GNP, GNPOld, LocalName, GovernmentForm, HeadOfState, Capital, Code2)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, country.getCode());
            preparedStatement.setString(2, country.getName());
            preparedStatement.setString(3, country.getContinent());
            preparedStatement.setString(4, country.getRegion());
            preparedStatement.setDouble(5, country.getSurfaceArea());
            preparedStatement.setInt(6, country.getIndepYear());
            preparedStatement.setLong(7, country.getPopulation());
            preparedStatement.setDouble(8, country.getLifeExpectancy());
            preparedStatement.setDouble(9, country.getGnp());
            preparedStatement.setDouble(10, country.getGnpOld());
            preparedStatement.setString(11, country.getLocalName());
            preparedStatement.setString(12, country.getGovernmentForm());
            preparedStatement.setString(13, country.getHeadOfState());
            preparedStatement.setInt(14, country.getCapital());
            preparedStatement.setString(15, country.getCode2());


            int rowsAffected  = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Country saved successfully");
            }
            else {
                System.out.println("Failed to save Country");
            }
        }

        return country;
    }

    @Override
    public void update(Country country) throws SQLException {
        String query = "UPDATE country SET Name = ?, Continent = ?, Region = ?, SurfaceArea = ?, IndepYear = ?," +
                " Population = ?, LifeExpectancy = ?, GNP = ?, GNPOld = ?, LocalName = ?," +
                " GovernmentForm = ?, HeadOfState = ?, Capital = ?, Code2 = ? WHERE Code = ?";
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, country.getName());
            preparedStatement.setString(2, country.getContinent());
            preparedStatement.setString(3, country.getRegion());
            preparedStatement.setDouble(4, country.getSurfaceArea());
            preparedStatement.setInt(5, country.getIndepYear());
            preparedStatement.setLong(6, country.getPopulation());
            preparedStatement.setDouble(7, country.getLifeExpectancy());
            preparedStatement.setDouble(8, country.getGnp());
            preparedStatement.setDouble(9, country.getGnpOld());
            preparedStatement.setString(10, country.getLocalName());
            preparedStatement.setString(11, country.getGovernmentForm());
            preparedStatement.setString(12, country.getHeadOfState());
            preparedStatement.setInt(13, country.getCapital());
            preparedStatement.setString(14, country.getCode2());
            preparedStatement.setString(15, country.getCode());

            int rowsAffected  = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Country updated successfully");
            }
            else {
                System.out.println("Failed to save Country");
            }
        }
    }

    @Override
    public void deleteByCode(String countryCode) throws SQLException {
        String query = "DELETE FROM country WHERE Code = ?";
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, countryCode);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Country deleted successfully");
            } else
                System.out.println("Failed to delete Country");
        }
    }
}
