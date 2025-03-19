package se.lexicon.model;

public class Country {
    private String code;
    private String name;
    private String continent;
    private String region;
    private double surfaceArea;
    private int indepYear;
    private long population;
    private double lifeExpectancy;
    private double gnp;
    private double gnpOld;
    private String localName;
    private String governmentForm;
    private String headOfState;
    private int capital;
    private String code2;

    public Country(String code, String name, String continent, String region,
                   double surfaceArea, int indepYear, long population, double lifeExpectancy,
                   double gnp, double gnpOld, String localName, String governmentForm,
                   String headOfState, int capital, String code2) {
        this.code = code;
        this.name = name;
        this.continent = continent;
        this.region = region;
        this.surfaceArea = surfaceArea;
        this.indepYear = indepYear;
        this.population = population;
        this.lifeExpectancy = lifeExpectancy;
        this.gnp = gnp;
        this.gnpOld = gnpOld;
        this.localName = localName;
        this.governmentForm = governmentForm;
        this.headOfState = headOfState;
        this.capital = capital;
        this.code2 = code2;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public double getSurfaceArea() {
        return surfaceArea;
    }

    public void setSurfaceArea(double surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    public int getIndepYear() {
        return indepYear;
    }

    public void setIndepYear(int indepYear) {
        this.indepYear = indepYear;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public double getLifeExpectancy() {
        return lifeExpectancy;
    }

    public void setLifeExpectancy(double lifeExpectancy) {
        this.lifeExpectancy = lifeExpectancy;
    }

    public double getGnp() {
        return gnp;
    }

    public void setGnp(double gnp) {
        this.gnp = gnp;
    }

    public double getGnpOld() {
        return gnpOld;
    }

    public void setGnpOld(double gnpOld) {
        this.gnpOld = gnpOld;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getGovernmentForm() {
        return governmentForm;
    }

    public void setGovernmentForm(String governmentForm) {
        this.governmentForm = governmentForm;
    }

    public String getHeadOfState() {
        return headOfState;
    }

    public void setHeadOfState(String headOfState) {
        this.headOfState = headOfState;
    }

    public int getCapital() {
        return capital;
    }

    public void setCapital(int capital) {
        this.capital = capital;
    }

    public String getCode2() {
        return code2;
    }

    public void setCode2(String code2) {
        this.code2 = code2;
    }

    @Override
    public String toString() {
        return String.format(
                "Country Details:\n" +
                        "-------------------------------------\n" +
                        "Code            : %s\n" +
                        "Name            : %s\n" +
                        "Continent       : %s\n" +
                        "Region          : %s\n" +
                        "Surface Area    : %.2f km²\n" +
                        "Independence    : %s\n" +
                        "Population      : %,d\n" +
                        "Life Expectancy : %.1f years\n" +
                        "GNP             : %.2f\n" +
                        "GNP (Old)       : %.2f\n" +
                        "Local Name      : %s\n" +
                        "Government      : %s\n" +
                        "Head of State   : %s\n" +
                        "Capital ID      : %s\n" +
                        "Code (Alt)      : %s\n",
                code, name, continent, region, surfaceArea,
                indepYear,
                population, lifeExpectancy, gnp, gnpOld,
                localName, governmentForm, headOfState,
                (capital != 0 ? capital : "N/A"), code2
        );
    }
}
