package se.lexicon.dao;

import se.lexicon.model.City;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CityDao {
    Optional<City> findById(int id) throws SQLException;

    List<City> findByCode(String code) throws SQLException;

    List<City> findByName(String name) throws SQLException;

    List<City> findAll() throws SQLException;

    City save(City city) throws SQLException;

    void update(City city) throws SQLException;

    void deleteById(int id) throws SQLException;
}
