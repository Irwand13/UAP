package CekelDuit.ui;

import CekelDuit.model.Transaction;
import CekelDuit.model.User;
import CekelDuit.utill.CurencyUtill;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HomePanel extends JPanel {

    private MainFrame mainFrame;
    private JLabel lblName, lblSaldo;
    private JPanel historyPanel;

    public HomePanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame ;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        setBackground(new Color(245,245,245));

        add(buildHeader(), BorderLayout.NORTH);
        add(buildHistoryCard(), BorderLayout.CENTER);
    }

    private JPanel buildHeader() {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        card.setBackground(new Color(0, 150, 136));

        lblName = new JLabel("-");
        lblName.setForeground(Color.WHITE);
        lblName.setFont(new Font("Segoe UI", Font.BOLD, 16));

        lblSaldo = new JLabel("Rp 0");
        lblSaldo.setForeground(Color.WHITE);
        lblSaldo.setFont(new Font("Segoe UI", Font.BOLD, 30));

        card.add(lblName);
        card.add(Box.createVerticalStrut(8));
        card.add(lblSaldo);

        return card;
    }

    private JPanel buildHistoryCard() {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(15,15,15,15)
        ));
        card.setBackground(Color.WHITE);

        JLabel title = new JLabel("Riwayat Transaksi");
        title.setFont(new Font("Segoe UI", Font.BOLD, 14));

        historyPanel = new JPanel();
        historyPanel.setLayout(new BoxLayout(historyPanel, BoxLayout.Y_AXIS));
        historyPanel.setBackground(Color.WHITE);

        card.add(title);
        card.add(Box.createVerticalStrut(10));
        card.add(historyPanel);
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 260));

        return card;
    }

    // 🔴 INI KUNCI UTAMA
    public void update(User user) {
        lblName.setText("Halo, " + user.getName());
        lblSaldo.setText(CurencyUtill.format(user.getBalance()));

        historyPanel.removeAll();

        List<Transaction> list = user.getTransactions()
                .stream()
                .limit(10)
                .toList();

        if (list.isEmpty()) {
            historyPanel.add(new JLabel("Belum ada transaksi"));
        } else {
            for (Transaction tx : list) {
                JLabel lbl = new JLabel(
                        (tx.isIncome() ? "➕ " : "➖ ")
                                + tx.getCategory()
                                + "   "
                                + CurencyUtill.format(tx.getAmount())
                );
                lbl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                historyPanel.add(lbl);
                historyPanel.add(Box.createVerticalStrut(6));
            }
        }

        historyPanel.revalidate();
        historyPanel.repaint();
    }
}
