package se.lexicon;

import se.lexicon.dao.CityDaoImpl;
import se.lexicon.dao.CountryDaoImpl;
import se.lexicon.dao.CountryLanguageDaoImpl;
import se.lexicon.model.City;
import se.lexicon.model.Country;
import se.lexicon.model.CountryLanguage;

import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Represents the entry point of the application.
 */

//TODO: handle Country.capital better
//TODO: maybe handle deletions of primary keys better, will currently crash when a class relies on CountryCode/Code
//TODO: more robust exception handling? who knows

public class Main {
    static CityDaoImpl cityDao = new CityDaoImpl();
    static CountryDaoImpl countryDao = new CountryDaoImpl();
    static CountryLanguageDaoImpl countryLanguageDao = new CountryLanguageDaoImpl();

    public static void main(String[] args) throws SQLException {
        boolean running = true;
        Scanner scanner = new Scanner(System.in);

        while (running) {
            System.out.println("Welcome to CRUDe World!");
            System.out.println("Please select an option (or q to exit): ");
            System.out.println("1) Create...");
            System.out.println("2) Look up...");
            System.out.println("3) Update...");
            System.out.println("4) Delete...");
            switch (scanner.nextLine()) {
                case "1":
                    displayAvailableDataMenu();
                    switch (scanner.nextLine()) {
                        case "1":
                            handleCreate(scanner, City.class);
                            break;
                        case "2":
                            handleCreate(scanner, Country.class);
                            break;
                        case "3":
                            handleCreate(scanner, CountryLanguage.class);
                            break;
                        case "q":
                            break;
                        default:
                            System.out.println("Invalid option. Please try again.");
                    }
                    break;
                case "2":
                    displayAvailableDataMenu();
                    switch (scanner.nextLine()) {
                        case "1":
                            handleSearchMenu(scanner, City.class, true);
                            break;
                        case "2":
                            handleSearchMenu(scanner, Country.class, true);
                            break;
                        case "3":
                            handleSearchMenu(scanner, CountryLanguage.class, true);
                            break;
                        case "q":
                            break;
                        default:
                            System.out.println("Invalid option. Please try again.");
                    }
                    break;
                case "3":
                    displayAvailableDataMenu();
                    switch (scanner.nextLine()) {
                        case "1":
                            handleUpdate(scanner, City.class);
                            break;
                        case "2":
                            handleUpdate(scanner, Country.class);
                            break;
                        case "3":
                            handleUpdate(scanner, CountryLanguage.class);
                            break;
                        case "q":
                            break;
                        default:
                            System.out.println("Invalid option. Please try again.");
                    }
                    break;
                case "4":
                    displayAvailableDataMenu();
                    switch (scanner.nextLine()) {
                        case "1":
                            handleDelete(scanner, City.class);
                            break;
                        case "2":
                            handleDelete(scanner, Country.class);
                            break;
                        case "3":
                            handleDelete(scanner, CountryLanguage.class);
                            break;
                        case "q":
                            break;
                        default:
                            System.out.println("Invalid option. Please try again.");
                    }
                    break;
                case "q":
                    running = false;
                    System.out.println("Thank you for using CRUDe World!");
                    scanner.close();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void displayAvailableDataMenu() {
        System.out.println("Please select an option (or q to return): ");
        System.out.println("1) City");
        System.out.println("2) Country");
        System.out.println("3) Language");
    }

    private static <T> void handleCreate(Scanner scanner, Class<T> createType) throws SQLException {
        if (createType == City.class) {
            String name = promptForInput("Name", scanner, String.class);
            String countryCode = promptForInput("Country Code", scanner, String.class);
            String district = promptForInput("District", scanner, String.class);
            int population = promptForInput("Population", scanner, Integer.class);

            City city = new City(name, countryCode, district, population);

            handleDisplay(city);
            System.out.println("Is this correct? Y/N");

            if (scanner.nextLine().equalsIgnoreCase("y"))
                cityDao.save(city);
            else {
                System.out.println("City not saved.");
                return;
            }
        }

        if (createType == Country.class) {
            String countryCode = promptForInput("Country Code", scanner, String.class);
            String name = promptForInput("Name", scanner, String.class);
            String continent = promptForInput("Continent", scanner, String.class);
            String region = promptForInput("Region", scanner, String.class);
            double surfaceArea = promptForInput("Surface area", scanner, Double.class);
            int indepYear = promptForInput("Independence year", scanner, Integer.class);
            long population = promptForInput("Population", scanner, Long.class);
            double lifeExpectancy = promptForInput("Life expectancy", scanner, Double.class);
            double gnp = promptForInput("GNP", scanner, Double.class);
            double gnpOld = promptForInput("GNP old", scanner, Double.class);
            String localName = promptForInput("Local name", scanner, String.class);
            String governmentForm = promptForInput("Government form", scanner, String.class);
            String headOfState = promptForInput("Head of state", scanner, String.class);
            int capitalId = promptForInput("Capital ID", scanner, Integer.class);
            String code2 = promptForInput("2-letter Country Code", scanner, String.class);

            Country country = new Country(countryCode, name, continent, region,
                    surfaceArea, indepYear, population, lifeExpectancy, gnp, gnpOld,
                    localName, governmentForm, headOfState, capitalId, code2);

            handleDisplay(country);
            System.out.println("Is this correct? Y/N");

            if (scanner.nextLine().equalsIgnoreCase("y"))
                countryDao.save(country);
            else {
                System.out.println("Country not saved.");
                return;
            }
        }

        if (createType == CountryLanguage.class) {
            String countryCode = promptForInput("Country Code", scanner, String.class);
            String languageName = promptForInput("Language Name", scanner, String.class);
            boolean isOfficial = promptForInput("Is Official", scanner, Boolean.class);
            double percentage = promptForInput("Percentage", scanner, Double.class);

            CountryLanguage countryLanguage = new CountryLanguage(countryCode, languageName, isOfficial, percentage);

            handleDisplay(countryLanguage);
            System.out.println("Is this correct? Y/N");

            if (scanner.nextLine().equalsIgnoreCase("y"))
                countryLanguageDao.save(countryLanguage);
            else {
                System.out.println("Language not saved.");
            }
        }
    }

    private static <T> void handleDelete(Scanner scanner, Class<T> deleteType) throws SQLException {
        Object obj = handleSearchMenu(scanner, deleteType, false);

        if (obj == null)
            return;

        if (deleteType == City.class) {
            Optional<City> selectedCity = (obj instanceof Optional<?>)
                    ? (Optional<City>) obj
                    : Optional.ofNullable((City) obj);

            selectedCity.ifPresent(city ->
                    System.out.printf("Are you sure you want to DELETE %s? Y/N\n",
                            city.getName()));
            if (scanner.nextLine().equalsIgnoreCase("y"))
                cityDao.deleteById(selectedCity
                        .orElseThrow(() -> new IllegalArgumentException("City not found."))
                        .getId());
            else
                return;
        }

        if (deleteType == Country.class) {
            Country selectedCountry = (Country) obj;

            System.out.printf("Are you sure you want to DELETE %s? Y/N\n", selectedCountry.getName());
            if (scanner.nextLine().equalsIgnoreCase("y"))
                countryDao.deleteByCode(selectedCountry
                        .getCode());
            else
                return;
        }

        if (deleteType == CountryLanguage.class) {
            CountryLanguage selectedLanguage = (CountryLanguage) obj;

            System.out.printf("Are you sure you want to DELETE %s? Y/N\n", selectedLanguage.getLanguage());
            if (scanner.nextLine().equalsIgnoreCase("y"))
                countryLanguageDao.deleteByCodeAndName(selectedLanguage.getCountryCode(), selectedLanguage.getLanguage());
        }
    }

    private static <T> void handleUpdate(Scanner scanner, Class<T> updateType) throws SQLException {
        Object obj = handleSearchMenu(scanner, updateType, false);
        String fakeForeignKey = "";

        if (obj == null)
            return;

        if (updateType == CountryLanguage.class)
            fakeForeignKey = ((CountryLanguage) obj).getLanguage();

        while (true) {
            System.out.println("Update... (or s to save, q to return)");
            if (updateType == City.class) {
                Optional<City> city = (obj instanceof Optional<?>)
                        ? (Optional<City>) obj
                        : Optional.ofNullable((City) obj);

                System.out.println("1) Name");
                System.out.println("2) Country Code");
                System.out.println("3) District");
                System.out.println("4) Population");

                switch (scanner.nextLine()) {
                    case "1":
                        city.map(City::getName).ifPresent(System.out::println);
                        String name = promptForInput("New name", scanner, String.class);
                        city.ifPresent(c -> c.setName(name));
                        break;
                    case "2":
                        city.map(City::getCountryCode).ifPresent(System.out::println);
                        String countryCode = promptForInput("New country code", scanner, String.class);
                        city.ifPresent(c -> c.setCountryCode(countryCode));
                        break;
                    case "3":
                        city.map(City::getDistrict).ifPresent(System.out::println);
                        String district = promptForInput("New district", scanner, String.class);
                        city.ifPresent(c -> c.setDistrict(district));
                        break;
                    case "4":
                        city.map(City::getPopulation).ifPresent(System.out::println);
                        int population = promptForInput("New population", scanner, Integer.class);
                        city.ifPresent(c -> c.setPopulation(population));
                        break;
                    case "s":
                        handleDisplay(city);
                        System.out.println("Does this look correct? Y/N");
                        if (scanner.nextLine().equalsIgnoreCase("y")) {
                            city.ifPresent(c -> {
                                try {
                                    cityDao.update(c);
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                            });
                        }

                        return;
                    case "q":
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }

            if (updateType == Country.class) {
                Country country = (Country) obj;

                System.out.println("1) Name");
                System.out.println("2) Continent");
                System.out.println("3) Region");
                System.out.println("4) Surface Area");
                System.out.println("5) Independence Year");
                System.out.println("6) Population");
                System.out.println("7) Life Expectancy");
                System.out.println("8) GNP");
                System.out.println("9) GNP (Old)");
                System.out.println("10) Local Name");
                System.out.println("11) Government Form");
                System.out.println("12) Head of State");
                System.out.println("13) Capital ID");
                System.out.println("14) Country Code (2-letter)");

                switch (scanner.nextLine()) {
                    case "1":
                        System.out.println(country.getName());
                        String name = promptForInput("New name", scanner, String.class);
                        country.setName(name);
                        break;
                    case "2":
                        System.out.println(country.getContinent());
                        String continent = promptForInput("New continent", scanner, String.class);
                        country.setContinent(continent);
                        break;
                    case "3":
                        System.out.println(country.getRegion());
                        String region = promptForInput("New region", scanner, String.class);
                        country.setRegion(region);
                        break;
                    case "4":
                        System.out.println(country.getSurfaceArea());
                        double surfaceArea = promptForInput("New surface area", scanner, Double.class);
                        country.setSurfaceArea(surfaceArea);
                        break;
                    case "5":
                        System.out.println(country.getIndepYear());
                        int indepYear = promptForInput("New independence year", scanner, Integer.class);
                        country.setIndepYear(indepYear);
                        break;
                    case "6":
                        System.out.println(country.getPopulation());
                        long population = promptForInput("New population", scanner, Long.class);
                        country.setPopulation(population);
                        break;
                    case "7":
                        System.out.println(country.getLifeExpectancy());
                        double lifeExpectancy = promptForInput("New name", scanner, Double.class);
                        country.setLifeExpectancy(lifeExpectancy);
                        break;
                    case "8":
                        System.out.println(country.getGnp());
                        double gnp = promptForInput("New GNP", scanner, Double.class);
                        country.setGnp(gnp);
                        break;
                    case "9":
                        System.out.println(country.getGnpOld());
                        double gnpOld = promptForInput("New GNP (old)", scanner, Double.class);
                        country.setGnpOld(gnpOld);
                        break;
                    case "10":
                        System.out.println(country.getLocalName());
                        String localName = promptForInput("New local name", scanner, String.class);
                        country.setLocalName(localName);
                        break;
                    case "11":
                        System.out.println(country.getGovernmentForm());
                        String governmentForm = promptForInput("New government form", scanner, String.class);
                        country.setGovernmentForm(governmentForm);
                        break;
                    case "12":
                        System.out.println(country.getHeadOfState());
                        String headOfState = promptForInput("New Head of State", scanner, String.class);
                        country.setHeadOfState(headOfState);
                        break;
                    case "13":
                        System.out.println(country.getCapital());
                        int capitalId = promptForInput("New capital ID", scanner, Integer.class);
                        country.setCapital(capitalId);
                        break;
                    case "14":
                        System.out.println(country.getCode2());
                        String code2 = promptForInput("New 2-letter country code", scanner, String.class);
                        country.setCode2(code2);
                        break;
                    case "s":
                        handleDisplay(country);
                        System.out.println("Does this look correct? Y/N");
                        if (scanner.nextLine().equalsIgnoreCase("y")) {
                            try {
                                countryDao.update(country);
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }

                        return;
                    case "q":
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }

            }

            if (updateType == CountryLanguage.class) {
                CountryLanguage countryLanguage = (CountryLanguage) obj;

                System.out.println("1) Language name");
                System.out.println("2) Official status");
                System.out.println("2) Percentage");

                switch (scanner.nextLine()) {
                    case "1":
                        System.out.println(countryLanguage.getLanguage());
                        String languageName = promptForInput("New language name", scanner, String.class);
                        countryLanguage.setLanguage(languageName);
                        break;
                    case "2":
                        System.out.println(countryLanguage.isOfficial());
                        boolean isOfficial = promptForInput("New official status", scanner, Boolean.class);
                        countryLanguage.setOfficial(isOfficial);
                        break;
                    case "3":
                        System.out.println(countryLanguage.getPercentage());
                        double percentage = promptForInput("New percentage", scanner, Double.class);
                        countryLanguage.setPercentage(percentage);
                        break;
                    case "s":
                        handleDisplay(countryLanguage);
                        System.out.println("Does this look correct? Y/N");
                        if (scanner.nextLine().equalsIgnoreCase("y")) {
                            try {
                                countryLanguageDao.update(countryLanguage, fakeForeignKey);
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }

                        return;
                    case "q":
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            }
        }
    }

    private static <T> void handleDisplay(T displayType) throws SQLException {
        if (displayType == null) {
            return;
        }

        if (displayType instanceof Optional) {
            ((Optional<?>) displayType).ifPresent(System.out::println);
        } else {
            System.out.println(displayType);
        }
    }

    private static <T> Object handleSearchResults(Scanner scanner, Object data, Class<T> searchType,
                                                  boolean displayResult) throws SQLException {
        Object result = null;
        AtomicInteger index = new AtomicInteger(1);

        if (data instanceof Collection) {
            Collection<?> collection = (Collection<?>) data;

            if (collection.isEmpty()) {
                System.out.println("No results found.");
                return Optional.empty();
            }

            if (collection.size() == 1) {
                result = collection.stream().findFirst();
                handleDisplay(result);

                return result;
            }

            collection.forEach(i -> {
                if (i instanceof City) {
                    City city = (City) i;
                    System.out.printf("%s) %s\n", index.getAndIncrement(), city.getName());
                } else if (i instanceof Country) {
                    Country country = (Country) i;
                    System.out.printf("%s) %s\n", index.getAndIncrement(), country.getName());
                } else if (i instanceof CountryLanguage) {
                    CountryLanguage countryLanguage = (CountryLanguage) i;
                    System.out.printf("%s) %s\n", index.getAndIncrement(), countryLanguage.getLanguage());
                }
            });

            AtomicInteger searchIndex = new AtomicInteger(1);
            if (searchType == City.class) {
                int id = promptForInput("ID", scanner, Integer.class);

                result = collection.stream()
                        .filter(obj -> obj instanceof City)
                        .filter(obj -> {
                            return searchIndex.getAndIncrement() == id;
                        })
                        .findFirst()
                        .orElse(null);
            } else if (searchType == Country.class) {
                int id = promptForInput("ID", scanner, Integer.class);

                result = collection.stream()
                        .filter(obj -> obj instanceof Country)
                        .filter(obj -> {
                            return searchIndex.getAndIncrement() == id;
                        })
                        .findFirst()
                        .orElse(null);
            } else if (searchType == CountryLanguage.class) {
                int id = promptForInput("ID", scanner, Integer.class);

                result = collection.stream()
                        .filter(obj -> obj instanceof CountryLanguage)
                        .filter(obj -> {
                            return searchIndex.getAndIncrement() == id;
                        })
                        .findFirst()
                        .orElse(null);
            }
            if (displayResult)
                handleDisplay(result);
        } else {
            if (displayResult)
                handleDisplay(data);
        }

        return (result != null) ? result : data;
    }

    private static <T> Object handleSearch(Scanner scanner, String option, Class<T> searchType,
                                           boolean displayResult) throws SQLException {
        if (searchType == City.class) {
            switch (option) {
                case "ID":
                    int id = promptForInput("ID", scanner, Integer.class);
                    return handleSearchResults(scanner, cityDao.findById(id), City.class, displayResult);
                case "Name":
                    String name = promptForInput("Name", scanner, String.class);
                    return handleSearchResults(scanner, cityDao.findByName(name), City.class, displayResult);
                case "Country Code":
                    String countryCode = promptForInput("Country Code", scanner, String.class);
                    return handleSearchResults(scanner, cityDao.findByCode(countryCode), City.class, displayResult);
                case "All":
                    return handleSearchResults(scanner, cityDao.findAll(), City.class, displayResult);
            }
        }

        if (searchType == Country.class) {
            switch (option) {
                case "Country Code":
                    String countryCode = promptForInput("Country Code", scanner, String.class);
                    return handleSearchResults(scanner, countryDao.findByCode(countryCode), Country.class, displayResult);
                case "Name":
                    String name = promptForInput("Name", scanner, String.class);
                    return handleSearchResults(scanner, countryDao.findByName(name), Country.class, displayResult);
                case "Continent":
                    String continent = promptForInput("Continent", scanner, String.class);
                    return handleSearchResults(scanner, countryDao.findByContinent(continent), Country.class, displayResult);
                case "Region":
                    String region = promptForInput("Region", scanner, String.class);
                    return handleSearchResults(scanner, countryDao.findByRegion(region), Country.class, displayResult);
                case "Capital":
                    int capital = promptForInput("Capital ID", scanner, Integer.class);
                    return handleSearchResults(scanner, countryDao.findByCapital(capital), Country.class, displayResult);
                case "All":
                    return handleSearchResults(scanner, countryDao.findAll(), Country.class, displayResult);
            }
        }

        if (searchType == CountryLanguage.class) {
            switch (option) {
                case "Country Code":
                    String countryCode = promptForInput("Country Code", scanner, String.class);
                    return handleSearchResults(scanner, countryLanguageDao.findByCode(countryCode), CountryLanguage.class, displayResult);
                case "Language name":
                    String language = promptForInput("Language name", scanner, String.class);
                    return handleSearchResults(scanner, countryLanguageDao.findByLanguageName(language), CountryLanguage.class, displayResult);
                case "All":
                    return handleSearchResults(scanner, countryLanguageDao.findAll(), CountryLanguage.class, displayResult);
                case "q":
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        return null;
    }

    private static <T> Object handleSearchMenu(Scanner scanner, Class<T> searchType, boolean displayResult) throws
            SQLException {
        if (searchType == City.class) {
            System.out.println("Search by... (or q to return): ");
            System.out.println("1) ID");
            System.out.println("2) Name");
            System.out.println("3) Country Code");
            System.out.println("4) All");
            String option = scanner.nextLine().trim();

            switch (option) {
                case "1":
                    return handleSearch(scanner, "ID", City.class, displayResult);
                case "2":
                    return handleSearch(scanner, "Name", City.class, displayResult);
                case "3":
                    return handleSearch(scanner, "Country Code", City.class, displayResult);
                case "4":
                    return handleSearch(scanner, "All", City.class, displayResult);
                case "q":
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    return handleSearchMenu(scanner, searchType, displayResult);
            }
        }

        if (searchType == Country.class) {
            System.out.println("Search by... (or q to return): ");
            System.out.println("1) Country Code");
            System.out.println("2) Name");
            System.out.println("3) Continent");
            System.out.println("4) Region");
            System.out.println("5) Capital");
            System.out.println("6) All");
            String option = scanner.nextLine().trim();

            switch (option) {
                case "1":
                    return handleSearch(scanner, "Country Code", Country.class, displayResult);
                case "2":
                    return handleSearch(scanner, "Name", Country.class, displayResult);
                case "3":
                    return handleSearch(scanner, "Continent", Country.class, displayResult);
                case "4":
                    return handleSearch(scanner, "Region", Country.class, displayResult);
                case "5":
                    return handleSearch(scanner, "Capital", Country.class, displayResult);
                case "6":
                    return handleSearch(scanner, "All", Country.class, displayResult);
                case "q":
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    return handleSearchMenu(scanner, searchType, displayResult);
            }
        }

        if (searchType == CountryLanguage.class) {
            System.out.println("Search by... (or q to return): ");
            System.out.println("1) Country code");
            System.out.println("2) Language name");
            System.out.println("3) All");
            String option = scanner.nextLine().trim();

            switch (option) {
                case "1":
                    return handleSearch(scanner, "Country Code", CountryLanguage.class, displayResult);
                case "2":
                    return handleSearch(scanner, "Language name", CountryLanguage.class, displayResult);
                case "3":
                    return handleSearch(scanner, "All", CountryLanguage.class, displayResult);
                case "q":
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    return handleSearchMenu(scanner, searchType, displayResult);
            }
        }

        return null;
    }

    private static <T> T promptForInput(String option, Scanner scanner, Class<T> type) {
        if (type == Boolean.class)
            System.out.printf("Enter %s, Y/NN: \n", option);
        else
            System.out.printf("Enter %s: \n", option);

        if (type == Integer.class) {
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();
            }

            int input = scanner.nextInt();
            scanner.nextLine();

            return type.cast(input);
        }

        if (type == Double.class) {
            while (!scanner.hasNextDouble()) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();
            }

            double input = scanner.nextDouble();
            scanner.nextLine();

            return type.cast(input);
        }

        if (type == Long.class) {
            while (!scanner.hasNextLong()) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();
            }

            long input = scanner.nextLong();
            scanner.nextLine();

            return type.cast(input);
        }

        if (type == String.class) {
            String input = scanner.nextLine();

            return type.cast(input);
        }


        if (type == Boolean.class) {
            String input = scanner.nextLine();
            boolean result;

            if (input.equalsIgnoreCase("y"))
                result = true;
            else if (input.equalsIgnoreCase("n"))
                result = false;
            else
                throw new IllegalArgumentException("Invalid input. Please enter 'y' or 'n'.");

            return type.cast(result);
        }

        return null;
    }
}
