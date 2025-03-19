package se.lexicon.model;

public class CountryLanguage {
    private String countryCode;
    private String language;
    private boolean isOfficial;
    private double percentage;

    public CountryLanguage(String countryCode, String language, boolean isOfficial, double percentage) {
        this.countryCode = countryCode;
        this.language = language;
        this.isOfficial = isOfficial;
        this.percentage = percentage;
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

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return String.format(
                "Country Language Details:\n" +
                        "-------------------------------------\n" +
                        "Country Code   : %s\n" +
                        "Language       : %s\n" +
                        "Official Status: %s\n" +
                        "Percentage     : %.2f%%\n",
                countryCode,
                language,
                (isOfficial ? "Official" : "Not Official"),
                percentage
        );

    }
}
