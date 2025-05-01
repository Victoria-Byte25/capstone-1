package com.pluralsight;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Handles all logic related to transactions such as:
 * - Adding new transactions
 * - Viewing all transactions (sorted by newest)
 * - Filtering transactions (by type, vendor, etc.)
 * - Saving/loading transactions to/from CSV
 */

public class TransactionManager {
    private List<Transaction> transactions = new ArrayList<>();
    private final String FILE_NAME = "transactions.csv";

    public TransactionManager() {
        loadTransactionsFromFile();  // Load from CSV at startup
    }

    public void addTransaction(Scanner scanner, String type) {
        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        if (type.equalsIgnoreCase("payment")) {
            amount = -Math.abs(amount); // Payments are stored negative
        }

        Transaction transaction = new Transaction(vendor, amount, type);
        transactions.add(transaction);
        writeTransactionToFile(transaction);  // Save to CSV
        System.out.println("Transaction added.");
    }

    public void listTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }

        System.out.println("=== Recent Transactions (Newest First) ===");
        for (int i = transactions.size() - 1; i >= 0; i--) {
            System.out.println(transactions.get(i));
        }
    }

    public void getBalance() {
        double total = 0;
        for (Transaction t : transactions) {
            total += t.getAmount();
        }
        System.out.printf("Current Balance: $%.2f\n", total);
    }

    private void writeTransactionToFile(Transaction t) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(String.format("%s,%s,%.2f,%s\n",
                    t.getType(), t.getVendor(), t.getAmount(), t.getFormattedTimestamp()));
        } catch (IOException e) {
            System.out.println("Error writing to CSV: " + e.getMessage());
        }
    }

    private void loadTransactionsFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 4);
                if (parts.length < 4) continue;

                String type = parts[0];
                String vendor = parts[1];
                double amount = Double.parseDouble(parts[2]);
                LocalDateTime timestamp = LocalDateTime.parse(parts[3]);

                transactions.add(new Transaction(vendor, amount, type, timestamp));
            }
        } catch (IOException e) {
            System.out.println("Error reading CSV: " + e.getMessage());
        }
    }
}