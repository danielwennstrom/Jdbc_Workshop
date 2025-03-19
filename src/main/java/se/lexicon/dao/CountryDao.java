package se.lexicon.dao;

import se.lexicon.model.City;
import se.lexicon.model.Country;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CountryDao {
    Country findByCode(String code) throws SQLException;

    List<Country> findByName(String name) throws SQLException;

    List<Country> findByContinent(String continent) throws SQLException;

    List<Country> findByRegion(String region) throws SQLException;

    List<Country> findByCapital(String capital) throws SQLException;

    List<Country> findAll() throws SQLException;

    Country save(Country country) throws SQLException;

    void update(Country country) throws SQLException;

    void deleteById(int id) throws SQLException;
}
