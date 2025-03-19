package se.lexicon.model;

/**
 * Represents a City entity based on the 'city' table in the 'world' database.
 */
public class City {
    private int id;
    private String name;
    private String countryCode;
    private String district;
    private int population;

    public City(int id, String name, String countryCode, String district, int population) {
        this.id = id;
        this.name = name;
        this.countryCode = countryCode;
        this.district = district;
        this.population = population;
    }

    public City(String name, String countryCode, String district, int population) {
        this.name = name;
        this.countryCode = countryCode;
        this.district = district;
        this.population = population;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return String.format(
                "City Details:\n" +
                        "-------------------------------------\n" +
                        "City Name      : %s\n" +
                        "Country Code   : %s\n" +
                        "District       : %s\n" +
                        "Population     : %,d\n",
                name,
                countryCode,
                district,
                population
        );
    }
}
