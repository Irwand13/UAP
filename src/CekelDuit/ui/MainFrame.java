package CekelDuit.ui;

import CekelDuit.model.Transaction;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {

    private double saldo = 0;
    private String username = "Pengguna";
    private final List<Transaction> transactions = new ArrayList<>();

    private HomePanel homePanel;

    public MainFrame() {
        setTitle("CekelDuit");
        setSize(420, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        homePanel = new HomePanel(this);
        setContentPane(homePanel);
    }

    // ===== LOGIC INTI =====
    public void addTransaction(Transaction tx) {
        if (tx.getType().equals("Pemasukan")) {
            saldo += tx.getAmount();
        } else {
            saldo -= tx.getAmount();
        }
        transactions.add(tx);
        refreshAll();
    }

    private void refreshAll() {
        homePanel.refresh();
    }

    // ===== GETTER =====
    public double getSaldo() {
        return saldo;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public List<Transaction> getLatestTransactions(int limit) {
        int size = transactions.size();
        return transactions.subList(Math.max(0, size - limit), size);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        refreshAll();
    }
}
