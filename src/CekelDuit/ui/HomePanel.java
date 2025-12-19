package CekelDuit.ui;

import CekelDuit.model.Transaction;
import CekelDuit.utill.CurencyUtill;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {

    private final MainFrame mainFrame;
    private JLabel lblName, lblSaldo;
    private JPanel historyPanel;

    public HomePanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        add(buildHeader(), BorderLayout.NORTH);
        add(buildHistoryCard(), BorderLayout.CENTER);
        setBackground(new Color(245,245,245));
    }

    private JPanel buildHeader() {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(new Color(0, 150, 136));
        card.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        lblName = new JLabel();
        lblName.setForeground(Color.WHITE);
        lblName.setFont(new Font("Segoe UI", Font.BOLD, 16));

        lblSaldo = new JLabel();
        lblSaldo.setForeground(Color.WHITE);
        lblSaldo.setFont(new Font("Segoe UI", Font.BOLD, 30));

        card.add(lblName);
        card.add(Box.createVerticalStrut(8));
        card.add(lblSaldo);
        card.setBackground(Color.WHITE);

        return card;
    }


    private JPanel buildHistoryCard() {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(15,15,15,15)
        ));

        JLabel title = new JLabel("Riwayat Transaksi");
        title.setFont(new Font("Segoe UI", Font.BOLD, 14));

        historyPanel = new JPanel();
        historyPanel.setLayout(new BoxLayout(historyPanel, BoxLayout.Y_AXIS));

        card.add(title);
        card.add(Box.createVerticalStrut(10));
        card.add(historyPanel);
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 260));

        add(Box.createVerticalStrut(20),BorderLayout.CENTER);

        return card;
    }

    public void refresh() {
        lblName.setText(mainFrame.getUsername());
        lblSaldo.setText(CurencyUtill.format(mainFrame.getSaldo()));

        historyPanel.removeAll();

        var list = mainFrame.getLatestTransactions(10);
        if (list.isEmpty()) {
            historyPanel.add(new JLabel("Belum ada transaksi"));
        } else {
            for (var tx : list) {
                JLabel lbl = new JLabel(
                        (tx.getType().equals("Pemasukan") ? "➕ " : "➖ ")
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