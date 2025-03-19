package se.lexicon;

import se.lexicon.dao.CityDaoImpl;
import se.lexicon.model.City;

import java.sql.SQLException;
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
                            int id;
                            String option = "ID";
                            Optional<City> city = Optional.empty();
                            List<City> cities;

                            System.out.println("Search by... (or q to return): ");
                            System.out.println("1) ID");
                            System.out.println("2) Name");
                            System.out.println("3) Country Code");
                            System.out.println("4) List all");
                            switch (scanner.nextLine()) {
                                case "1":
                                    id = promptForIntInput(option, scanner);
                                    city = cityDao.findById(id);
                                    break;
                                case "2":
                                    option = "Name";
                                    String name = promptForStringInput(option, scanner);
                                    cities = cityDao.findByName(name);
                                    displayCitySearchResults(cities);

                                    option = "ID";
                                    id = promptForIntInput(option, scanner);
                                    displayCity(city, cityDao, id);
                                    break;
                                case "3":
                                    option = "Country Code";
                                    String countryCode = promptForStringInput(option, scanner);
                                    cities = cityDao.findByCode(countryCode);
                                    displayCitySearchResults(cities);


                                    id = promptForIntInput(option, scanner);
                                    displayCity(city, cityDao, id);
                                    break;
                                case "4":
                                    cities = cityDao.findAll();
                                    displayCitySearchResults(cities);

                                    option = "ID";
                                    id = promptForIntInput(option, scanner);
                                    displayCity(city, cityDao, id);
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

    private static void displayCity(Optional<City> city, CityDaoImpl cityDao, int id) throws SQLException {
        city = cityDao.findById(id);
        System.out.println(city);
    }

    private static void displayCitySearchResults(List<City> cities) {
        for (City c : cities) {
            System.out.printf("%s) %s\n", c.getId(), c.getName());
        }
    }

    private static int promptForIntInput(String option, Scanner scanner) {
        System.out.printf("Enter %s: \n", option);
        int id = scanner.nextInt();
        scanner.nextLine();
        return id;
    }

    private static String promptForStringInput(String option, Scanner scanner) {
        String line;
        System.out.printf("Enter %s: \n", option);
        line = scanner.nextLine();
        return line;
    }
}
