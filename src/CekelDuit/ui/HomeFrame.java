package CekelDuit.ui;

import CekelDuit.ui.components.CardPanel;
import CekelDuit.ui.components.ColorPalette;
import CekelDuit.ui.components.RoundedButton;

import javax.swing.*;
import java.awt.*;

public class HomeFrame extends JFrame {

    public HomeFrame() {
        setTitle("CekelDuit");
        setSize(420, 760);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(ColorPalette.BG);

        add(buildContent(), BorderLayout.CENTER);
    }

    private JScrollPane buildContent() {
        JPanel container = new JPanel();
        container.setBackground(ColorPalette.BG);
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        container.add(buildGreeting());
        container.add(Box.createVerticalStrut(12));
        container.add(buildSaldoCard());
        container.add(Box.createVerticalStrut(12));
        container.add(buildQuoteCard());
        container.add(Box.createVerticalStrut(12));
        container.add(buildQuickMenu());
        container.add(Box.createVerticalStrut(12));
        container.add(buildHistory());

        return new JScrollPane(container,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    private JPanel buildGreeting() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(ColorPalette.BG);

        JLabel lbl = new JLabel("Halo, Arek Malang 👋🏼");
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 18));

        p.add(lbl, BorderLayout.WEST);
        return p;
    }

    private JPanel buildSaldoCard() {
        CardPanel card = new CardPanel();
        card.setBackground(new Color(220, 252, 231));

        JLabel title = new JLabel("Saldo Cekel Wallet");
        title.setForeground(ColorPalette.TEXT_GRAY);

        JLabel saldo = new JLabel("Rp 1.250.000");
        saldo.setFont(new Font("Segoe UI", Font.BOLD, 28));

        JPanel btnPanel = new JPanel(new GridLayout(1, 2, 12, 0));
        btnPanel.setOpaque(false);

        btnPanel.add(new RoundedButton("Catat Masuk", ColorPalette.INCOME));
        btnPanel.add(new RoundedButton("Catat Keluar", ColorPalette.EXPENSE));

        card.add(title);
        card.add(Box.createVerticalStrut(8));
        card.add(saldo);
        card.add(Box.createVerticalStrut(16));
        card.add(btnPanel);

        return card;
    }

    private JPanel buildQuoteCard() {
        CardPanel card = new CardPanel();

        JLabel quote = new JLabel(
                "<html><i>“Duit itu kayak temen, harus dijaga baik-baik!”</i></html>");
        quote.setForeground(ColorPalette.TEXT_GRAY);

        card.add(quote);
        return card;
    }

    private JPanel buildQuickMenu() {
        JPanel grid = new JPanel(new GridLayout(1, 2, 12, 0));
        grid.setOpaque(false);

        grid.add(buildMenuItem("🐷 Celengan Cekel"));
        grid.add(buildMenuItem("📊 Lihat Laporan"));

        return grid;
    }

    private JPanel buildMenuItem(String text) {
        CardPanel card = new CardPanel();
        JLabel lbl = new JLabel(text, SwingConstants.CENTER);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 14));

        card.add(lbl);
        return card;
    }

    private JPanel buildHistory() {
        CardPanel card = new CardPanel();

        JLabel title = new JLabel("Riwayat Transaksi");
        title.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JLabel item1 = new JLabel("🍜 Makan Siang - Rp 15.000");
        JLabel item2 = new JLabel("⛽ Bensin - Rp 20.000");

        JLabel link = new JLabel("<html><u>Lihat Semua</u></html>");
        link.setForeground(ColorPalette.PRIMARY);

        card.add(title);
        card.add(Box.createVerticalStrut(8));
        card.add(item1);
        card.add(item2);
        card.add(Box.createVerticalStrut(8));
        card.add(link);

        return card;
    }
}
