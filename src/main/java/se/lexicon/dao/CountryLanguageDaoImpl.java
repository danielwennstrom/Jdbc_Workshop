package se.lexicon.dao;

import se.lexicon.model.City;
import se.lexicon.model.CountryLanguage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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
        return List.of();
    }

    @Override
    public List<CountryLanguage> findByLanguageName(String name) throws SQLException {
        return List.of();
    }

    @Override
    public List<CountryLanguage> findAll() throws SQLException {
        return List.of();
    }

    @Override
    public CountryLanguage save(CountryLanguage language) throws SQLException {
        return null;
    }

    @Override
    public void update(CountryLanguage language) throws SQLException {

    }

    @Override
    public void deleteById(int id) throws SQLException {

    }
}
