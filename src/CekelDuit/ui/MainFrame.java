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


    public MainFrame(User user) {
        setTitle("CekelDuit");
        setSize(420, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        profilePanel = new ProfilePanel(this);
        homePanel = new HomePanel(this);
        historyPanel = new HistoryPanel(this);
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
        historyPanel.refresh();
        profilePanel.refresh();
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
        homePanel.refresh();
        cardLayout.show(mainPanel, "HOME");
    }

    public void showHistory() {
        historyPanel.refresh();
        cardLayout.show(mainPanel, "HISTORY");
    }
    public void showProfile() {
        profilePanel.refresh();
        cardLayout.show(mainPanel, "PROFILE");
    }
    public void showTransaction() {
        cardLayout.show(mainPanel, "TRANSAKSI");
    }
}
