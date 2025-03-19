package se.lexicon.dao;

import se.lexicon.model.Country;
import se.lexicon.model.CountryLanguage;

import java.sql.ResultSet;
import java.sql.SQLException;
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
    public List<Country> findByCode(String code) throws SQLException {
        return List.of();
    }

    @Override
    public List<Country> findByName(String name) throws SQLException {
        return List.of();
    }

    @Override
    public List<Country> findByContinent(String continent) throws SQLException {
        return List.of();
    }

    @Override
    public List<Country> findByRegion(String region) throws SQLException {
        return List.of();
    }

    @Override
    public List<Country> findByCapital(String capital) throws SQLException {
        return List.of();
    }

    @Override
    public List<Country> findAll() throws SQLException {
        return List.of();
    }

    @Override
    public Country save(Country country) throws SQLException {
        return null;
    }

    @Override
    public void update(Country country) throws SQLException {

    }

    @Override
    public void deleteById(int id) throws SQLException {

    }
}
