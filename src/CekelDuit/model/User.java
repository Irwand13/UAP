package CekelDuit.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String id;
    private String name;
    private double balance;
    private List<Transaction> transactions;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        this.balance = 0;
        this.transactions = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getBalance() { return balance; }
    public List<Transaction> getTransactions() { return transactions; }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
