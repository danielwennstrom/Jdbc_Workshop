package se.lexicon.dao;

import se.lexicon.db.DBConnection;
import se.lexicon.model.CountryLanguage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryLanguageDaoImpl implements CountryLanguageDao {
    private CountryLanguage mapRow(ResultSet resultSet) throws SQLException {
        return new CountryLanguage(
                resultSet.getString("CountryCode"),
                resultSet.getString("Language"),
                resultSet.getBoolean("IsOfficial"),
                resultSet.getDouble("Percentage")
        );
    }

    @Override
    public List<CountryLanguage> findByCode(String code) throws SQLException {
        String query = "SELECT * FROM countrylanguage WHERE CountryCode = ?";
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, code);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<CountryLanguage> languages = new ArrayList<>();

            while (resultSet.next()) {
                CountryLanguage language = mapRow(resultSet);
                languages.add(language);
            }

            return languages;
        }
    }

    @Override
    public List<CountryLanguage> findByLanguageName(String name) throws SQLException {
        String query = "SELECT * FROM countrylanguage WHERE Language = ?";
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<CountryLanguage> languages = new ArrayList<>();

            while (resultSet.next()) {
                CountryLanguage language = mapRow(resultSet);
                languages.add(language);
            }

            return languages;
        }
    }

    @Override
    public List<CountryLanguage> findAll() throws SQLException {
        String query = "SELECT * FROM countrylanguage";
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<CountryLanguage> languages = new ArrayList<>();

            while (resultSet.next()) {
                CountryLanguage language = mapRow(resultSet);
                languages.add(language);
            }

            return languages;
        }
    }

    @Override
    public CountryLanguage save(CountryLanguage language) throws SQLException {
        String query = "INSERT INTO countrylanguage (CountryCode, Language, IsOfficial, Percentage)" +
                " VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, language.getCountryCode());
            preparedStatement.setString(2, language.getLanguage());
            preparedStatement.setBoolean(3, language.isOfficial());
            preparedStatement.setDouble(4, language.getPercentage());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Language saved successfully");
            } else {
                System.out.println("Failed to save Language");
            }

            return language;
        }
    }

    @Override
    public void update(CountryLanguage language) throws SQLException {
        String query = "UPDATE countrylanguage SET Language = ?, IsOfficial = ?, Percentage = ?" +
                " WHERE CountryCode = ?";
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, language.getLanguage());
            preparedStatement.setBoolean(2, language.isOfficial());
            preparedStatement.setDouble(3, language.getPercentage());
            preparedStatement.setString(4, language.getCountryCode());
            preparedStatement.setString(6, language.getCountryCode());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Language updated successfully");
            } else {
                System.out.println("Failed to update Language");
            }
        }
    }

    @Override
    public void deleteByCode(String countryCode) throws SQLException {
        String query = "DELETE FROM countrylanguage WHERE CountryCode = ?";
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, countryCode);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Language deleted successfully");
            } else
                System.out.println("Failed to delete Language");
        }
    }
}
