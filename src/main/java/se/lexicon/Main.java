package se.lexicon;

import se.lexicon.dao.CityDaoImpl;
import se.lexicon.model.City;

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
                    break;
                case "2":
                    System.out.println("Please select an option (or q to return): ");
                    System.out.println("1) City");
                    System.out.println("2) Country");
                    System.out.println("3) Language");
                    switch (scanner.nextLine()) {
                        case "1":
                            System.out.println("Search by... (or q to return): ");
                            System.out.println("1) ID");
                            System.out.println("2) Name");
                            System.out.println("3) Country Code");
                            System.out.println("4) All");
                            switch (scanner.nextLine()) {
                                case "1":
                                    handleCitySearch("ID", scanner, cityDao);
                                    break;
                                case "2":
                                    handleCitySearch("Name", scanner, cityDao);
                                    break;
                                case "3":
                                    handleCitySearch("Country Code", scanner, cityDao);
                                    break;
                                case "4":
                                    handleCitySearch("All", scanner, cityDao);
                                    break;
                                case "q":
                                    return;
                                default:
                                    System.out.println("Invalid option. Please try again.");
                            }
                            break;
                        case "2":
                            break;
                        case "3":
                            break;
                        case "q":
                            return;
                        default:
                            System.out.println("Invalid option. Please try again.");
                    }
                    break;
                case "3":
                    break;
                case "4":
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

    private static Optional<Integer> handleCitySearch(String searchType, Scanner scanner, CityDaoImpl cityDao) throws SQLException {
        List<City> cities = new ArrayList<>();

        switch (searchType) {
            case "ID":
                Optional<City> city;
                int id = promptForInput("ID", scanner, Integer.class);
                city = cityDao.findById(id);
                displayCity(city);
                return Optional.of(id);
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

            return Optional.of(cities.get(0).getId());
        }

        displayCitySearchResults(cities);
        int id = promptForInput("ID", scanner, Integer.class);
        Optional<City> selectedCity = cityDao.findById(id);
        displayCity(selectedCity);

        return Optional.of(id);
    }


    private static void displayCity(Optional<City> city) throws SQLException {
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

        if (type == String.class) {
            String input = scanner.nextLine();

            return type.cast(input);
        }

        return null;
    }
}
