package CekelDuit.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String type; // "Pemasukan" / "Pengeluaran"
    private double amount;
    private String category;
    private String note;
    private String  createdAt;

    private static final DateTimeFormatter FORMAT =
            DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public Transaction(String type, double amount, String category, String note) {
        this.type = type;
        this.amount = amount;
        this.category = category;
        this.note = note;
        this.createdAt = LocalDateTime.now().format(FORMAT);
    }

    public String getType() {
        return type; }

    public double getAmount() {
        return amount; }

    public String getCategory() {
        return category; }

    public String getNote() {
        return note; }

    public boolean isIncome() {
        return type.equalsIgnoreCase("Pemasukan");
    }

    public String CreatedAt() {
        return createdAt;
    }
}
