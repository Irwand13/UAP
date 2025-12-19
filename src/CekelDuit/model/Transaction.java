package CekelDuit.model;

public class Transaction {
    private String type; // "Pemasukan" / "Pengeluaran"
    private double amount;
    private String category;
    private String note;

    public Transaction(String type, double amount, String category, String note) {
        this.type = type;
        this.amount = amount;
        this.category = category;
        this.note = note;
    }

    public String getType() { return type; }
    public double getAmount() { return amount; }
    public String getCategory() { return category; }
    public String getNote() { return note; }

    public boolean isIncome() {
        return type.equalsIgnoreCase("Pemasukan");
    }
}
