package se.lexicon.model;

public class CountryLanguage {
    private int id;
    private String countryCode;
    private String language;
    private boolean isOfficial;

    public CountryLanguage(int id, String countryCode, String language, boolean isOfficial) {
        this.id = id;
        this.countryCode = countryCode;
        this.language = language;
        this.isOfficial = isOfficial;
    }

    public CountryLanguage(String countryCode, String language, boolean isOfficial) {
        this.countryCode = countryCode;
        this.language = language;
        this.isOfficial = isOfficial;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isOfficial() {
        return isOfficial;
    }

    public void setOfficial(boolean official) {
        isOfficial = official;
    }
}
