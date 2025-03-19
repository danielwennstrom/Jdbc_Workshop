package se.lexicon.dao;

import se.lexicon.model.City;
import se.lexicon.model.CountryLanguage;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CountryLanguageDao {
    CountryLanguage findByCode(String code) throws SQLException;

    List<CountryLanguage> findByLanguageName(String name) throws SQLException;

    List<CountryLanguage> findAll() throws SQLException;

    CountryLanguage save(CountryLanguage language) throws SQLException;

    void update(CountryLanguage language) throws SQLException;

    void deleteByCode(String code) throws SQLException;
}
