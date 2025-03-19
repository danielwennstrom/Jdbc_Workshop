package se.lexicon;

import java.util.Scanner;

/**
 * Represents the entry point of the application.
 */
public class Main {
    public static void main(String[] args) {
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
                            String option;

                            System.out.println("Search by... (or q to return): ");
                            System.out.println("1) ID");
                            System.out.println("2) Name");
                            System.out.println("3) Country Code");
                            System.out.println("4) List all");
                            switch (scanner.nextLine()) {
                                case "1":
                                    option = "ID";
                                    int id = promptForIntInput(option, scanner);
                                    break;
                                case "2":
                                    option = "Name";
                                    String name = promptForStringInput(option, scanner);
                                    break;
                                case "3":
                                    option = "Country Code";
                                    String countryCode = promptForStringInput(option, scanner);
                                    break;
                                case "4":
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
