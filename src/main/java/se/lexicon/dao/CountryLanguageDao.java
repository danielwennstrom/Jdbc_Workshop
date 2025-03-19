package se.lexicon.dao;

import se.lexicon.model.CountryLanguage;

import java.sql.SQLException;
import java.util.List;

public interface CountryLanguageDao {
    List<CountryLanguage> findByCode(String code) throws SQLException;

    List<CountryLanguage> findByLanguageName(String name) throws SQLException;

    List<CountryLanguage> findAll() throws SQLException;

    CountryLanguage save(CountryLanguage language) throws SQLException;

    void update(CountryLanguage language, String oldLanguageName) throws SQLException;

    void deleteByCodeAndName(String code, String languageName) throws SQLException;
}
