package com.pluralsight;

import java.util.Scanner;


public class FlexFundApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TransactionManager transactionManager = new TransactionManager();
        GoalTracker goalTracker = new GoalTracker();
        QuoteGenerator quoteGenerator = new QuoteGenerator();

        // Quote when app opens
        System.out.println("Welcome to FlexFund App!");
        System.out.println("\"" + quoteGenerator.getRandomFunnyQuote() + "\"");

        // The display menu that handles user choices

        boolean running = true;
        while (running) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Add a deposit");
            System.out.println("2. Add a payment");
            System.out.println("3. View all transactions");
            System.out.println("4. View savings and spending progress");
            System.out.println("5. View balance");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // newline

            switch (choice) {
                case 1:
                    transactionManager.addTransaction(scanner, "deposit");
                    break;
                case 2:
                    transactionManager.addTransaction(scanner, "payment");
                    break;
                case 3:
                    transactionManager.listTransactions();
                    break;
                case 4:
                    goalTracker.showProgress();
                    break;
                case 5:
                    transactionManager.getBalance();
                    break;
                case 6:
                    running = false;
                    System.out.println("Thanks for using FlexFund. See you next time!");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
    }
}








