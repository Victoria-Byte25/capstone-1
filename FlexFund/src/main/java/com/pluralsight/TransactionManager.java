package com.pluralsight;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class TransactionManager {
    private List <Transaction> transactions = new ArrayList<>();
    private final String File_Name = "transactions.csv";

    public TransactionManager() {
        loadTransactionsFromFile(); // Loads transaction from start
    }

    public void addTransaction(Scanner scanner, String type) {
        System.out.println("Enter vendor");
        String vendor = scanner.next();

        System.out.println("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        if (type.equals("payment")) {
            amount = -Math.abs(amount); // payments are stored negative diff. from deposit
        }

        Transaction transaction = new Transaction(vendor, amount);
        transactions.add(transaction);
        writeTransactionToFile(transactions); // save to CSV
        System.out.println("Transaction added.");

    }

    public void listTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }
        System.out.println("=== Recent Transactions (Newest First) ===");
            for (int i = transactions.size() -1; i >= 0; i--) {
                System.out.println(transactions.get(i));
            }
        }
    }

    public double getBalance() {
        double total = 0;
        for (Transaction t : transactions) {
            total += t.getAmount();
        }
        return total;
    }

    public int getTransactionCount() {
        return transactions.size();
    }



}
