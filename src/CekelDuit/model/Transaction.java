package CekelDuit.model;

import java.time.LocalDate;

public class Transaction {

    public enum Type {
        INCOME, EXPENSE
    }

    private Type type;
    private double amount;
    private String category;
    private String note;
    private LocalDate date;

    public Transaction(Type type, double amount, String category, String note, LocalDate date) {
        this.type = type;
        this.amount = amount;
        this.category = category;
        this.note = note;
        this.date = date;
    }

    // ===== GETTER =====
    public Type getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getNote() {
        return note;
    }

    public LocalDate getDate() {
        return date;
    }

    // ===== HELPER =====
    public boolean isIncome() {
        return type == Type.INCOME;
    }

    public boolean isExpense() {
        return type == Type.EXPENSE;
    }
}
