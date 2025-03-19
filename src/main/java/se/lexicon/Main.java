package se.lexicon;

import se.lexicon.dao.CityDaoImpl;
import se.lexicon.dao.CountryDaoImpl;
import se.lexicon.dao.CountryLanguageDaoImpl;
import se.lexicon.model.City;
import se.lexicon.model.Country;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Represents the entry point of the application.
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        CityDaoImpl cityDao = new CityDaoImpl();
        CountryDaoImpl countryDao = new CountryDaoImpl();
        CountryLanguageDaoImpl countryLanguageDao = new CountryLanguageDaoImpl();
        boolean running = true;
        Scanner scanner = new Scanner(System.in);
        Optional<City> selectedCity = Optional.empty();

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
                            handleCityCreate(scanner, cityDao);
                            break;
                        case "2":
                            handleCountryCreate(scanner, countryDao);
                            break;
                        case "3":
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
                            handleCitySearchReturn(scanner, selectedCity, cityDao);
                            break;
                        case "2":
                            break;
                        case "3":
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
                            handleCityUpdate(scanner, selectedCity, cityDao);
                            break;
                        case "2":
                            break;
                        case "3":
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
                            selectedCity = handleCitySearchReturn(scanner, selectedCity, cityDao);
                            if (!selectedCity.isPresent())
                                break;

                            selectedCity.ifPresent(city ->
                                    System.out.printf("Are you sure you want to DELETE %s? Y/N\n",
                                    city.getName()));
                            if (scanner.nextLine().equalsIgnoreCase("y"))
                                cityDao.deleteById(selectedCity
                                        .orElseThrow(() -> new IllegalArgumentException("City not found."))
                                        .getId());
                            else
                                return;
                            break;
                        case "2":
                            break;
                        case "3":
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

    private static void handleCountryCreate(Scanner scanner, CountryDaoImpl countryDao) throws SQLException {
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
                localName, governmentForm,headOfState, capitalId, code2);

        countryDao.save(country);
    }

    private static void handleCityCreate(Scanner scanner, CityDaoImpl cityDao) throws SQLException {
        String name = promptForInput("Name", scanner, String.class);
        String countryCode = promptForInput("Country Code", scanner, String.class);
        String district = promptForInput("District", scanner, String.class);
        int population = promptForInput("Population", scanner, Integer.class);

        City city = new City(name, countryCode, district, population);
        cityDao.save(city);
    }

    private static void handleCityUpdate(Scanner scanner, Optional<City> selectedCity, CityDaoImpl cityDao) throws SQLException {
        selectedCity = handleCitySearchReturn(scanner, selectedCity, cityDao);
        if (!selectedCity.isPresent())
            return;

        while (true) {
            System.out.println("Update... (or s to save, q to return)");
            System.out.println("1) Name");
            System.out.println("2) Country Code");
            System.out.println("3) District");
            System.out.println("4) Population");
            switch (scanner.nextLine()) {
                case "1":
                    selectedCity.map(City::getName).ifPresent(System.out::println);
                    String name = promptForInput("New name", scanner, String.class);
                    selectedCity.ifPresent(city -> city.setName(name));
                    break;
                case "2":
                    selectedCity.map(City::getCountryCode).ifPresent(System.out::println);
                    String countryCode = promptForInput("New country code", scanner, String.class);
                    selectedCity.ifPresent(city -> city.setCountryCode(countryCode));
                    break;
                case "3":
                    selectedCity.map(City::getDistrict).ifPresent(System.out::println);
                    String district = promptForInput("New district", scanner, String.class);
                    selectedCity.ifPresent(city -> city.setDistrict(district));
                    break;
                case "4":
                    selectedCity.map(City::getPopulation).ifPresent(System.out::println);
                    int population = promptForInput("New population", scanner, Integer.class);
                    selectedCity.ifPresent(city -> city.setPopulation(population));
                    break;
                case "s":
                    selectedCity.ifPresent(city -> {
                        try {
                            cityDao.update(city);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    });

                    return;
                case "q":
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static Optional<City> handleCitySearchReturn(Scanner scanner, Optional<City> selectedCity, CityDaoImpl cityDao) throws SQLException {
        System.out.println("Search by... (or q to return): ");
        System.out.println("1) ID");
        System.out.println("2) Name");
        System.out.println("3) Country Code");
        System.out.println("4) All");
        switch (scanner.nextLine()) {
            case "1":
                selectedCity = handleCitySearch("ID", scanner, cityDao);
                break;
            case "2":
                selectedCity = handleCitySearch("Name", scanner, cityDao);
                break;
            case "3":
                selectedCity = handleCitySearch("Country Code", scanner, cityDao);
                break;
            case "4":
                selectedCity = handleCitySearch("All", scanner, cityDao);
                break;
            case "q":
                return Optional.empty();
            default:
                System.out.println("Invalid option. Please try again.");
        }
        return selectedCity;
    }

    private static void displayAvailableDataMenu() {
        System.out.println("Please select an option (or q to return): ");
        System.out.println("1) City");
        System.out.println("2) Country");
        System.out.println("3) Language");
    }

    private static Optional<City> handleCitySearch(String searchType, Scanner scanner, CityDaoImpl cityDao) throws SQLException {
        List<City> cities = new ArrayList<>();

        switch (searchType) {
            case "ID":
                Optional<City> city;
                int id = promptForInput("ID", scanner, Integer.class);
                city = cityDao.findById(id);
                displayCity(city);
                return city;
            case "Name":
                String name = promptForInput("Name", scanner, String.class);
                cities = cityDao.findByName(name);
                break;
            case "Country Code":
                String countryCode = promptForInput("Country Code", scanner, String.class);
                cities = cityDao.findByCode(countryCode);
                break;
            case "All":
                cities = cityDao.findAll();
                break;
        }

        if (cities.isEmpty()) {
            System.out.println("No results found.");

            return Optional.empty();
        }

        if (cities.size() == 1) {
            displayCity(Optional.of(cities.get(0)));

            return Optional.of(cities.get(0));
        }

        displayCitySearchResults(cities);
        int id = promptForInput("ID", scanner, Integer.class);
        Optional<City> selectedCity = cityDao.findById(id);
        displayCity(selectedCity);

        return selectedCity;
    }


    private static void displayCity(Optional<City> city) {
        city.ifPresent(System.out::println);
    }

    private static void displayCitySearchResults(List<City> cities) {
        for (City c : cities) {
            System.out.printf("%s) %s\n", c.getId(), c.getName());
        }
    }

    private static <T> T promptForInput(String option, Scanner scanner, Class<T> type) {
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

        return null;
    }
}
