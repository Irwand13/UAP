package CekelDuit.ui;

import CekelDuit.model.Transaction;
import CekelDuit.model.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {

    private double saldo = 0;
    private HistoryPanel historyPanel;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private String username = "Pengguna";
    private final List<Transaction> transactions = new ArrayList<>();
    private HomePanel homePanel;
    private ProfilePanel profilePanel;
    private TransactionPanel transactionPanel;
    private User user;


    public MainFrame(User user) {
        this.user = user;
        setTitle("CekelDuit");
        setSize(420, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        profilePanel = new ProfilePanel(this);
        homePanel = new HomePanel();
        historyPanel = new HistoryPanel();
        transactionPanel = new TransactionPanel(this);

        mainPanel.add(homePanel, "HOME");
        mainPanel.add(transactionPanel, "TX");
        mainPanel.add(historyPanel, "HISTORY");
        mainPanel.add(profilePanel, "PROFILE");


        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        add(buildBottomNav(), BorderLayout.SOUTH);
        showHome(); // tampilkan Home pertama


    }
    private JPanel buildBottomNav() {
        JPanel nav = new JPanel(new GridLayout(1, 3));

        JButton btnHome = new JButton("🏠 Home");
        JButton btnHistory = new JButton("📊 Riwayat");
        JButton btnProfile = new JButton("👤 Profil");
        JButton btnTx = new JButton(" + Transaksi");

        btnHome.addActionListener(e -> showHome());
        btnHistory.addActionListener(e -> showHistory());
        btnProfile.addActionListener(e -> showProfile());
        btnTx.addActionListener(e -> showTransaction());

        nav.add(btnHome);
        nav.add(btnTx);
        nav.add(btnHistory);
        nav.add(btnProfile);

        return nav;
    }

    public void deleteTransaction(int index) {
        Transaction tx = user.getTransactions().get(index);

        // rollback saldo
        if (tx.isIncome()) {
            user.setBalance(user.getBalance() - tx.getAmount());
        } else {
            user.setBalance(user.getBalance() + tx.getAmount());
        }

        user.getTransactions().remove(index);
        refreshAll();
    }

    public void editTransaction(int index) {
        Transaction oldTx = user.getTransactions().get(index);

        TransactionDialog dialog =
                new TransactionDialog(this, oldTx, index);

        dialog.setVisible(true);
    }



    // ===== LOGIC INTI =====
    public void addTransaction(Transaction tx) {
        // 1. Simpan ke user
        user.getTransactions().add(tx);

        // 2. Update saldo user
        if (tx.isIncome()) {
            user.setBalance(user.getBalance() + tx.getAmount());
        } else {
            user.setBalance(user.getBalance() - tx.getAmount());
        }

        // 3. Update tampilan Home
        homePanel.update(user);

        // 4. (opsional nanti) simpan ke JSON
    }

    public void updateTransaction(int index, Transaction newTx) {
        Transaction oldTx = user.getTransactions().get(index);

        // rollback saldo lama
        if (oldTx.isIncome()) {
            user.setBalance(user.getBalance() - oldTx.getAmount());
        } else {
            user.setBalance(user.getBalance() + oldTx.getAmount());
        }

        // apply transaksi baru
        user.getTransactions().set(index, newTx);

        if (newTx.isIncome()) {
            user.setBalance(user.getBalance() + newTx.getAmount());
        } else {
            user.setBalance(user.getBalance() - newTx.getAmount());
        }

        refreshAll();
    }


    private void refreshAll() {
        homePanel.update(user);
        historyPanel.update(user);
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
    
    public void showHome() {
        homePanel.update(user);
        cardLayout.show(mainPanel, "HOME");
    }

    public void showHistory() {
        historyPanel.update(user);
        cardLayout.show(mainPanel, "HISTORY");
    }
    public void showProfile() {
        profilePanel.refresh();
        cardLayout.show(mainPanel, "PROFILE");
    }
    public void showTransaction() {
        cardLayout.show(mainPanel, "TRANSAKSI");
    }
    public User getUser() {
        return user;
    }
}
