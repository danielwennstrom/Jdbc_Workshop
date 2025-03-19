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
            System.out.println("Please select an option (or q to exit):");
            switch (scanner.nextLine()) {
                case "1":
                    break;
                case "2":
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
}
