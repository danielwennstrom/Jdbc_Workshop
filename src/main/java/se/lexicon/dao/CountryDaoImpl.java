package se.lexicon.dao;

import se.lexicon.model.Country;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CountryDaoImpl implements CountryDao {
    @Override
    public Optional<Country> findById(int id) throws SQLException {
        return Optional.empty();
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
