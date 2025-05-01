package com.pluralsight;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

//Each transaction has a date, vendor, amount, and a description.

public class Transaction {
    private String vendor;  // vendor - who the transaction was with
    private double amount; // amount - how much was spent or received
    private String type; // type - notes about the transaction
    private LocalDateTime timestamp; // date - when the transaction happened

    public Transaction(String vendor, double amount, String type) {
        this.vendor = vendor;
        this.amount = amount;
        this.type = type;
        this.timestamp = LocalDateTime.now();
    }
    //For loading from CSV
    public Transaction(String vendor, double amount, String type, LocalDateTime timestamp){
        this.vendor = vendor;
        this.amount = amount;
        this.type = type;
        this.timestamp = timestamp;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public String getFormattedTimestamp() {
        return timestamp.toString(); // saves time in standard format
    }
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("%s | %s | $%.2f | %s",
                type.toUpperCase(), vendor, Math.abs(amount), formatter.format(timestamp));

    }
}
