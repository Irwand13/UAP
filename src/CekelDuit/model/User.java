package CekelDuit.model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String id;
    private String name;
    private double balance;
    private List<Transaction> transactions = new ArrayList<>();

    public User(){}

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        this.balance = 0;
        this.transactions = new ArrayList<>();
    }
    public List<Transaction> getTransactions() {
        if (transactions == null) {
            transactions = new ArrayList<>();
        }
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions ;
    }
    // getter & setter
    public String getId() { return id; }
    public String getName() { return name; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

}
