package CekelDuit.model;

public class Wallet {

    private double balance;
    private double totalIncome;
    private double totalExpense;

    public Wallet() {
        this.balance = 0;
        this.totalIncome = 0;
        this.totalExpense = 0;
    }

    public void addIncome(double amount) {
        totalIncome += amount;
        balance += amount;
    }

    public void addExpense(double amount) {
        totalExpense += amount;
        balance -= amount;
    }

    // ===== GETTER =====
    public double getBalance() {
        return balance;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public double getTotalExpense() {
        return totalExpense;
    }
}
