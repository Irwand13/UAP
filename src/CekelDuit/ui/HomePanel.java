package CekelDuit.ui;

import CekelDuit.model.Transaction;
import CekelDuit.utill.CurencyUtill;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {

    private final MainFrame mainFrame;

    private JLabel lblSaldo;
    private JPanel historyPanel;

    public HomePanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        add(buildHeader(), BorderLayout.NORTH);
        add(buildContent(), BorderLayout.CENTER);
    }

    private JPanel buildHeader() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(0, 150, 136));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblTitle = new JLabel("Saldo Kamu");
        lblTitle.setForeground(Color.WHITE);

        lblSaldo = new JLabel("Rp 0");
        lblSaldo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblSaldo.setForeground(Color.WHITE);

        panel.add(lblTitle);
        panel.add(Box.createVerticalStrut(8));
        panel.add(lblSaldo);

        return panel;
    }

    private JPanel buildContent() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setOpaque(false);

        JButton btnAdd = new JButton("➕ Catat Transaksi");
        btnAdd.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 14));

        btnAdd.addActionListener(e -> {
            TransactionDialog dialog = new TransactionDialog(mainFrame);
            dialog.setVisible(true);
        });

        panel.add(btnAdd);
        panel.add(Box.createVerticalStrut(20));

        // CARD Riwayat
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        card.setBackground(Color.WHITE);

        JLabel title = new JLabel("Transaksi Terakhir");
        title.setFont(new Font("Segoe UI", Font.BOLD, 14));

        historyPanel = new JPanel();
        historyPanel.setLayout(new BoxLayout(historyPanel, BoxLayout.Y_AXIS));
        historyPanel.setOpaque(false);

        card.add(title);
        card.add(Box.createVerticalStrut(10));
        card.add(historyPanel);

        panel.add(card);

        return panel;
    }

    // ===== REFRESH UI =====
    public void refresh() {
        lblSaldo.setText(
                CurencyUtill.format(mainFrame.getSaldo())
        );

        historyPanel.removeAll();

        if (mainFrame.getTransactions().isEmpty()) {
            historyPanel.add(new JLabel("Belum ada transaksi"));
        } else {
            for (Transaction tx : mainFrame.getLatestTransactions(3)) {
                JLabel lbl = new JLabel(
                        (tx.getType().equals("Pemasukan") ? "➕ " : "➖ ")
                                + tx.getCategory()
                                + " - "
                                + CurencyUtill.format(tx.getAmount())
                );
                historyPanel.add(lbl);
                historyPanel.add(Box.createVerticalStrut(5));
            }
        }

        historyPanel.revalidate();
        historyPanel.repaint();
    }
}