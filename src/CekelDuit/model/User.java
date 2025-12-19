package CekelDuit.model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String id;
    private String name;
    private double balance;
    private List<Transaction> transactions = new ArrayList<>();

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // getter & setter
    public String getId() { return id; }
    public String getName() { return name; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
