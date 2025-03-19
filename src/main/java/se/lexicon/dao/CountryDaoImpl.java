package se.lexicon.dao;

import se.lexicon.model.City;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CountryDaoImpl implements CountryDao {
    @Override
    public Optional<City> findById(int id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<City> findByCode(String code) throws SQLException {
        return List.of();
    }

    @Override
    public List<City> findByName(String name) throws SQLException {
        return List.of();
    }

    @Override
    public List<City> findAll() throws SQLException {
        return List.of();
    }

    @Override
    public City save(City city) throws SQLException {
        return null;
    }

    @Override
    public void update(City city) throws SQLException {

    }

    @Override
    public void deleteById(int id) throws SQLException {

    }
}
